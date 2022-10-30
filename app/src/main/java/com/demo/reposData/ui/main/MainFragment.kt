package com.demo.reposData.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.reposData.R
import com.demo.reposData.common.*
import com.demo.reposData.data.model.JackwhartonResponseItem
import com.demo.reposData.databinding.FragmentMainBinding
import com.demo.reposData.ui.adapter.AdapterReposPagination
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeoutException

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var mAdapter: AdapterReposPagination
    private val pageStart: Int = 1
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private var totalPages: Int = 1
    private var currentPage: Int = pageStart

    companion object{
        const val TOTAL_PAGES = 15
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMyOrderRecyclerView()
        collectData()

    }

    private fun initUI() {

        with(binding) {

            rvReposs.adapter = mAdapter

        }
    }

    private fun collectData() {

        with(binding) {

            with(mainViewModel) {

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    reposDataFirstFlow.collect { result ->

                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()

                                mAdapter.addAll(result.data)
                                totalPages = TOTAL_PAGES

                                if (currentPage <= totalPages) mAdapter.addLoadingFooter()
                                else isLastPage = true


                            }
                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }

                }


                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    reposDataNextFlow.collect { result ->
                        when (result) {
                            is Resource.Success -> {
                                progressBar.gone()
                                if (result.data.isEmpty()) {
                                    mAdapter.removeLoadingFooter()
                                    isLastPage = true
                                    return@collect
                                }
                                mAdapter.removeLoadingFooter()
                                isLoading = false
                                mAdapter.addAll(result.data)

                                if (currentPage != totalPages) mAdapter.addLoadingFooter()
                                else if (result.data.isEmpty()) isLastPage = true
                                else isLastPage = true

                            }
                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }

                }



                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    reposDataOfflineFlow.collect { result ->
                        when (result) {
                            is Resource.Success -> {
                                progressBar.gone()
                                var newList: List<JackwhartonResponseItem> = mutableListOf()
                                for (item in result.data.indices) {
                                    newList = result.data.map {
                                        JackwhartonResponseItem(
                                            id = result.data[item].id,
                                            name = result.data[item].title,
                                            description = result.data[item].description
                                        )
                                    }
                                    mAdapter.add(newList[item])
                                }
                                totalPages = 1
                                isLastPage = true

                            }
                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }

                }
            }
        }
    }

    private fun initMyOrderRecyclerView() {
        //attach adapter to  recycler
        mAdapter = AdapterReposPagination(this)
        initUI()
        binding.rvReposs.setHasFixedSize(true)
        binding.rvReposs.itemAnimator = DefaultItemAnimator()

        loadFirstPage()

        binding.rvReposs.addOnScrollListener(object :
            PaginationScrollListener(binding.rvReposs.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1

                Handler(Looper.myLooper()!!).postDelayed({
                    loadNextPage()
                }, 1000)
            }

            override fun getTotalPageCount(): Int {
                return totalPages
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })
    }

    private fun loadFirstPage() {
        //hideErrorView()
        if (CheckValidation.isConnected(context ?: activity?.applicationContext!!)) {
            mainViewModel.reposDataFirstPage(currentPage)
        } else {
            mainViewModel.getDataFromOffline()
        }
    }

    fun loadNextPage() {
        if (CheckValidation.isConnected(context ?: activity?.applicationContext!!)) {
            mainViewModel.reposDataNextPage(currentPage)
        } else {
            mAdapter.showRetry(true, fetchErrorMessage(null))
        }
    }

    private fun fetchErrorMessage(throwable: Throwable?): String {
        var errorMsg: String = resources.getString(R.string.error_msg_unknown)

        if (!CheckValidation.isConnected(context ?: activity?.applicationContext!!)) {
            errorMsg = resources.getString(R.string.error_msg_no_internet)
        } else if (throwable is TimeoutException) {
            errorMsg = resources.getString(R.string.error_msg_timeout)
        }

        return errorMsg
    }


}