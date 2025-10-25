package com.ssu.portfolio.newsreaderapp.ui

import android.Manifest
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssu.portfolio.newsreaderapp.Repository.NewsRepository
import com.ssu.portfolio.newsreaderapp.models.Article
import com.ssu.portfolio.newsreaderapp.models.NewsResponse
import com.ssu.portfolio.newsreaderapp.utils.Resourses
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import android.annotation.SuppressLint

class NewsViewModel(
    app: Application,
    private val newsRepository: NewsRepository
) : AndroidViewModel(app) {

    val headlines = MutableLiveData<Resourses<NewsResponse>>()
    var headlinePage = 1
    private var headlineResponse: NewsResponse? = null

    val searchResults = MutableLiveData<Resourses<NewsResponse>>()
    var searchPage = 1
    private var searchResponse: NewsResponse? = null
    private var newSearchQuery: String? = null
    private var oldSearchQuery: String? = null

    init {
        getHeadlines("us")
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getHeadlines(countryCode: String) = viewModelScope.launch {
        fetchHeadlines(countryCode)
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun searchNews(query: String) = viewModelScope.launch {
        if (newSearchQuery != query) {
            searchResponse = null
            searchPage = 1
        }
        newSearchQuery = query
        fetchSearchResults(query)
    }

    private fun handleHeadlinesResponse(response: Response<NewsResponse>): Resourses<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                headlinePage++
                if (headlineResponse == null) {
                    headlineResponse = result
                } else {
                    headlineResponse?.articles?.addAll(result.articles)
                }
                return Resourses.success(headlineResponse ?: result)
            }
        }
        return Resourses.Error(response.message())
    }

    private fun handleSearchResponse(response: Response<NewsResponse>): Resourses<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                if (searchResponse == null || newSearchQuery != oldSearchQuery) {
                    searchPage = 1
                    oldSearchQuery = newSearchQuery
                    searchResponse = result
                } else {
                    searchPage++
                    searchResponse?.articles?.addAll(result.articles)
                }
                return Resourses.success(searchResponse ?: result)
            }
        }
        return Resourses.Error(response.message())
    }

    fun addToFavourites(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getFavoritesNews() = newsRepository.getFavoritesNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    @Suppress("DEPRECATION")
    private fun hasInternetConnection(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo != null && networkInfo.isConnected
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private suspend fun fetchHeadlines(countryCode: String) {
        headlines.postValue(Resourses.Loading())
        try {
            if (hasInternetConnection(getApplication())) {
                val response = newsRepository.getHeadlines(countryCode, headlinePage)
                headlines.postValue(handleHeadlinesResponse(response))
            } else {
                headlines.postValue(Resourses.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> headlines.postValue(Resourses.Error("Network Failure. Please check your connection."))
                else -> headlines.postValue(Resourses.Error("Conversion Error: ${t.message}"))
            }
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private suspend fun fetchSearchResults(query: String) {
        searchResults.postValue(Resourses.Loading())
        try {
            if (hasInternetConnection(getApplication())) {
                val response = newsRepository.searchNews(query, searchPage)
                searchResults.postValue(handleSearchResponse(response))
            } else {
                searchResults.postValue(Resourses.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> searchResults.postValue(Resourses.Error("Network Failure. Please check your connection."))
                else -> searchResults.postValue(Resourses.Error("Conversion Error: ${t.message}"))
            }
        }
    }
}
