package com.kono.remote_interview_android.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kono.remote_interview_android.databinding.FragmentArticleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding

    private val viewModel: ArticleViewModel by viewModel()

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
    }
}