package com.kono.remote_interview_android.ui.article.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.kono.konosdk.data.Articles
import com.kono.remote_interview_android.databinding.AdapterArticleFrontCoverBinding
import com.kono.remote_interview_android.databinding.AdapterArticlePartBinding
import com.kono.remote_interview_android.helper.UrlHelper

class ArticleAdapter(private val urlHelper: UrlHelper) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    companion object {
        private const val FRONT_COVER_INDEX = 0
    }

    private var context: Context? = null
    private val items = ArrayList<Articles.Child>()
    fun setItems(articles: Articles) {
        items.clear()
        items.addAll(articles.children)
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
                    val smallThumbnailKonoUrl = urlHelper.covertToKonoUrl(url, smallThumbnail)
                    val bigThumbnailKonoUrl = urlHelper.covertToKonoUrl(url, bigThumbnail)
                    loadImageUrl(viewBinding.imageView, smallThumbnailKonoUrl, bigThumbnailKonoUrl)

                }
            }
            ArticleType.PART.ordinal -> {

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

    private fun loadImageUrl(
        imageView: ImageView,
        smallThumbnailKonoUrl: String,
        bigThumbnailKonoUrl: String
    ) {
        Glide
            .with(context!!)
            .load(bigThumbnailKonoUrl)
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