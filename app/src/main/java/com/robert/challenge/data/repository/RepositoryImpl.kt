package com.robert.challenge.data.repository

import com.robert.challenge.api.FlickrAPIService
import com.robert.challenge.data.model.PhotoModel
import com.robert.challenge.data.model.ResponseModel
import com.robert.challenge.data.remote.ApiResult
import com.robert.challenge.data.remote.safeApiCallWithRetry
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: FlickrAPIService) : FlickrRepository {
    override suspend fun searchPhotos(tags: String): ApiResult<List<PhotoModel>> {
        return safeApiCallWithRetry(maxRetries = 3, initialDelayMillis = 1000) {
            val response = apiService.searchPhotos(tags = tags)
            response.items
        }
    }

    override suspend fun fetchPhotos(): ApiResult<List<PhotoModel>> {
        return safeApiCallWithRetry(maxRetries = 3, initialDelayMillis = 1000) {
            val response = apiService.fetchPhotos()
            response.items
        }
    }
}