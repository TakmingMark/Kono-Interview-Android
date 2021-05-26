package com.kono.remote_interview_android.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kono.remote_interview_android.databinding.FragmentArticleBinding
import com.kono.remote_interview_android.ui.article.recyclerview.ArticleAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding

    private val viewModel: ArticleViewModel by viewModel()

    private val articleAdapter: ArticleAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView")
        binding = FragmentArticleBinding.inflate(layoutInflater)
            .apply {

            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initLiveDataListener()
        initData()
    }

    private fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = articleAdapter
    }

    private fun initLiveDataListener() {
        viewModel.callArticlesDataFailure.observe(viewLifecycleOwner, { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        })

        viewModel.updateArticlesData.observe(viewLifecycleOwner, { articles ->
            articleAdapter.setItems(articles)
        })
    }

    private fun initData() {
        viewModel.callArticlesData()
    }

}