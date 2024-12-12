package com.robert.challenge.data.repository

import com.robert.challenge.data.model.PhotoModel
import com.robert.challenge.data.model.ResponseModel
import com.robert.challenge.data.remote.ApiResult

interface FlickrRepository {
    suspend fun searchPhotos(tags: String): ApiResult<List<PhotoModel>>
}