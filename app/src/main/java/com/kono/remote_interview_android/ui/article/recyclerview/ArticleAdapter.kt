package com.kono.remote_interview_android.ui.article.recyclerview

import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.kono.konosdk.data.Articles
import com.kono.remote_interview_android.databinding.AdapterArticleFrontCoverBinding
import com.kono.remote_interview_android.databinding.AdapterArticlePartBinding
import com.kono.remote_interview_android.helper.BitmapHelper
import com.kono.remote_interview_android.helper.UrlHelper
import timber.log.Timber

class ArticleAdapter(private val urlHelper: UrlHelper, private val bitmapHelper: BitmapHelper) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    companion object {
        private const val FRONT_COVER_INDEX = 0
    }

    private var context: Context? = null
    private val items = ArrayList<Articles.Child>()
    fun setItems(articles: Articles) {
        items.clear()
        items.addAll(articles.children.sortedBy { child ->
            child.orderInParent
        })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (context == null)
            context = parent.context

        val binding = when (viewType) {
            ArticleType.FRONT_COVER.ordinal -> {
                AdapterArticleFrontCoverBinding.inflate(LayoutInflater.from(context), parent, false)
            }
            ArticleType.PART.ordinal -> {
                AdapterArticlePartBinding.inflate(LayoutInflater.from(context), parent, false)
            }
            else -> {
                AdapterArticlePartBinding.inflate(LayoutInflater.from(context), parent, false)
            }
        }

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        when (getItemViewType(position)) {
            ArticleType.FRONT_COVER.ordinal -> {
                val viewBinding = holder.viewBinding as AdapterArticleFrontCoverBinding
                viewBinding.titleTextView.text = item.name
                viewBinding.contentTextView.text = item.description
                if (item.res.image.list.isNotEmpty() && item.res.image.list[0].thumbnails.size > 3) {
                    //Uri is little weired
                    val url = item.res.image.list[0].uri
                    val smallThumbnail = item.res.image.list[0].thumbnails[0]
                    val bigThumbnail = item.res.image.list[0].thumbnails[3]
                    val bigThumbnailKonoUrl = urlHelper.covertToKonoUrl(url, bigThumbnail)
                    loadFrontCoverImageUrl(viewBinding.imageView, bigThumbnailKonoUrl)
                }
            }
            ArticleType.PART.ordinal -> {
                val viewBinding = holder.viewBinding as AdapterArticlePartBinding
                viewBinding.titleTextView.text = item.name
                viewBinding.contentTextView.text = item.description



                Timber.d("${viewBinding.contentTextView.height}+${viewBinding.contentTextView.lineHeight}")
                if (item.res.image.list.isNotEmpty() && item.res.image.list[0].thumbnails.size > 3) {
                    //Uri is little weired
                    val url = item.res.image.list[0].uri
                    val smallThumbnail = item.res.image.list[0].thumbnails[0]
                    val bigThumbnail = item.res.image.list[0].thumbnails[3]
                    val smallThumbnailKonoUrl = urlHelper.covertToKonoUrl(url, smallThumbnail)
                    val bigThumbnailKonoUrl = urlHelper.covertToKonoUrl(url, bigThumbnail)
                    loadPartImageUrl(
                        viewBinding.imageView,
                        smallThumbnailKonoUrl,
                        bigThumbnailKonoUrl
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].orderInParent == FRONT_COVER_INDEX) {
            ArticleType.FRONT_COVER.ordinal
        } else {
            ArticleType.PART.ordinal
        }
    }

    private fun loadFrontCoverImageUrl(
        imageView: ImageView,
        bigThumbnailKonoUrl: String
    ) {
        Glide
            .with(context!!)
            .asBitmap()
            .thumbnail(0.3f)
            .load(bigThumbnailKonoUrl)
            .fitCenter()
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val cutBitmap = bitmapHelper.cutBitmap(resource, 1.0, 0.4)
                    when (context!!.resources.configuration.orientation) {
                        Configuration.ORIENTATION_LANDSCAPE -> {
                            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                        }
                        Configuration.ORIENTATION_PORTRAIT -> {
                            imageView.scaleType = ImageView.ScaleType.FIT_CENTER
                        }
                    }

                    imageView.setImageBitmap(cutBitmap)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })

    }

    private fun loadPartImageUrl(
        imageView: ImageView,
        smallThumbnailKonoUrl: String,
        bigThumbnailKonoUrl: String
    ) {
        Glide
            .with(context!!)
            .load(bigThumbnailKonoUrl)
            .centerCrop()
            .thumbnail(
                Glide.with(context!!)
                    .load(smallThumbnailKonoUrl)
            )
            .into(imageView)
    }

    class ViewHolder(val viewBinding: ViewBinding) : RecyclerView.ViewHolder(viewBinding.root)

    enum class ArticleType {
        FRONT_COVER,
        PART
    }
}