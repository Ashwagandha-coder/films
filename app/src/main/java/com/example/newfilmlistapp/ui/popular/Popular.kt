package com.example.newfilmlistapp.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newfilmlistapp.R
import com.example.newfilmlistapp.view_model.ViewModel_Popular
import com.example.newfilmlistapp.databinding.FragmentPopularBinding
import com.example.newfilmlistapp.model.ResultPopular
import com.example.newfilmlistapp.ui.recycler_view.RecyclerViewScrollListener
import com.example.newfilmlistapp.ui.recycler_view.ScrollBack


class Popular : Fragment(), ScrollBack {

    // field

    private var allMovies = arrayListOf<ResultPopular>()
    private var totalResults: Int = -1
    private var isLoading: Boolean = false

    private lateinit var binding: FragmentPopularBinding

    private val viewModel: ViewModel_Popular by lazy {
        ViewModelProvider(this).get(ViewModel_Popular::class.java)
    }

    private val popularAdapter: PopularAdapter = PopularAdapter()
    private val mScrollListener by lazy { RecyclerViewScrollListener(this) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(inflater,container,false)

        workWithViewModel()
        setFragmentTitle()
        setRecyclerView()

        return binding.root
    }


    fun workWithViewModel() {
        viewModel.requestPopular()
        viewModel.popularMovie.observe(viewLifecycleOwner) {

            totalResults = it.results.data.totalResults
            allMovies.addAll(it.data.results)
            popularAdapter.submitList(allMovies)
            isLoading = false

        }


    }

    private fun setRecyclerView() {
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = popularAdapter
            addOnScrollListener(mScrollListener)
        }
    }

    private fun setFragmentTitle() {
        val string = "Popular Movie"
        binding.tView.apply {
            text = string
        }
    }

    override fun onScrollCompleted(firstVisibleItem: Int, isLoadingMoreData: Boolean) {
        if (allMovies.size != totalResults) {
            if (!isLoading) {
                isLoading = true
                viewModel.requestPopular()
            }
        }
    }


}