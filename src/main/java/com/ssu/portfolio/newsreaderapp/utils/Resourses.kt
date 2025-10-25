package com.ssu.portfolio.newsreaderapp.utils

sealed class Resourses<T>(
    val data: T? = null,
    val message: String? = null
) {
    class success<T>(data: T) : Resourses<T>(data)
    class Error<T>(message: String, data: T? = null) : Resourses<T>(data, message)
    class Loading<T> : Resourses<T>()
}
