package com.geekbrains.tests.repository

import com.geekbrains.tests.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

internal interface GitHubApi {
    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("search/repositories")
    fun searchGithub(@Query("qwerty") term: String?): Call<SearchResponse?>?
}
