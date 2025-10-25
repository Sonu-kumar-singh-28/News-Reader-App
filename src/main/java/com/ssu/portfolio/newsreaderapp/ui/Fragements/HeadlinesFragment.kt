package com.ssu.portfolio.newsreaderapp.ui.Fragements

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssu.portfolio.newsreaderapp.R
import com.ssu.portfolio.newsreaderapp.adaptor.NewsAdaptor
import com.ssu.portfolio.newsreaderapp.databinding.FragmentHeadlinesBinding
import com.ssu.portfolio.newsreaderapp.ui.NewsActivity
import com.ssu.portfolio.newsreaderapp.ui.NewsViewModel
import com.ssu.portfolio.newsreaderapp.utils.Constants
import com.ssu.portfolio.newsreaderapp.utils.Constants.Companion.QUERY_PAGE_SIZE
import com.ssu.portfolio.newsreaderapp.utils.Resourses

class HeadlinesFragment : Fragment(R.layout.fragment_headlines) {
    lateinit var newsViewModel: NewsViewModel
    lateinit var newsAdaptor: NewsAdaptor
    lateinit var retryButton: Button
    lateinit var errorText: TextView
    lateinit var itemheadLinesError: CardView
    private lateinit var binding: FragmentHeadlinesBinding

    var isError = false
    var isLoading = false
    var isLastPage = false
    var isScrolling = false
    var headlinesPage = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeadlinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemheadLinesError = view.findViewById(R.id.itemHeadlinesError)
        retryButton = view.findViewById(R.id.retryButton)
        errorText = view.findViewById(R.id.errorText)

        newsViewModel = (activity as NewsActivity).newsViewModel
        setupHeadLineRecyclers()

        if (newsViewModel.headlines.value == null) {
            newsViewModel.getHeadlines("us")
        }

        newsAdaptor.setOnItemClickListener { article ->
            val bundle = Bundle().apply {
                putSerializable("article", article)
            }
            findNavController().navigate(
                R.id.action_favoritesFragment_to_articlesFragment,
                bundle
            )
        }

        newsViewModel.headlines.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resourses.success -> {
                    hideProgressBar()
                    hideErrorMessage()
                    response.data?.let { newsResponse ->
                        newsAdaptor.differ.submitList(newsResponse.articles.toList())

                        val totalPages = newsResponse.totalResults / QUERY_PAGE_SIZE + 2
                        isLastPage = newsViewModel.headlinePage == totalPages
                        if(isLastPage) {
                            binding.recyclerHeadlines.setPadding(0, 0, 0, 0)
                        }
                    }
                }
                is Resourses.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        ShowErrorMesage("An error occurred: $message")
                        retryButton.setOnClickListener {
                            newsViewModel.getHeadlines("us")
                        }
                    }
                }
                is Resourses.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideErrorMessage() {
        itemheadLinesError.visibility = View.INVISIBLE
        isError = false
    }

    private fun ShowErrorMesage(message: String) {
        itemheadLinesError.visibility = View.VISIBLE
        errorText.text = message
        isError = true
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNoErrors = !isError
            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val usAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= Constants.QUERY_PAGE_SIZE

            val shouldPaginate =
                isNoErrors && isNotLoadingAndNotLastPage && usAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {
                newsViewModel.getHeadlines("us")
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

    private fun setupHeadLineRecyclers() {
        newsAdaptor = NewsAdaptor()
        binding.recyclerHeadlines.apply {
            adapter = newsAdaptor
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@HeadlinesFragment.scrollListener)
        }
    }
}