package com.example.moviecatalouge

import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {

    @GET("/3/movie/popular?api_key=a4688006ee424e76debb7ee88b97bb72")
    fun getMovieList(): Call<MovieResponse>

    @GET("/3/tv/popular?api_key=a4688006ee424e76debb7ee88b97bb72")
    fun getTvList(): Call<TvResponse>
}