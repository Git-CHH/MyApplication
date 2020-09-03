package com.example.examapplication.service

import com.example.examapplication.model.Photo
import io.reactivex.Maybe
import retrofit2.http.GET

interface PhotoApi {
    @GET("albums/1/photos")
    fun getListPhoto (): Maybe<List<Photo>>
}