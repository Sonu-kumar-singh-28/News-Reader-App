package com.ssu.portfolio.newsreaderapp.Repository

import com.ssu.portfolio.newsreaderapp.api.RetrofitInstance
import com.ssu.portfolio.newsreaderapp.db.ArticleDatabase
import com.ssu.portfolio.newsreaderapp.models.Article

class NewsRepository(val  db: ArticleDatabase) {

    suspend fun getHeadlines(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getHeadlines(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchfornews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getFavoritesNews() = db.getArticleDao().getAllArticles()

    suspend fun  deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}