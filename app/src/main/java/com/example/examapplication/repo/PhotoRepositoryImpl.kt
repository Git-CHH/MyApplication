package com.example.examapplication.repo

import com.example.examapplication.service.PhotoApi
import com.example.examapplication.model.Photo
import io.reactivex.Maybe

interface PhotoRepository{
    fun getListPhoto(): Maybe<List<Photo>>
}
class PhotoRepositoryImpl (private val service: PhotoApi) :
    PhotoRepository {

    override fun getListPhoto(): Maybe<List<Photo>> {
        return service.getListPhoto()
    }

}