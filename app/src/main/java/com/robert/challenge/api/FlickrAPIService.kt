package com.robert.challenge.api

import com.robert.challenge.data.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrAPIService {

        @GET("services/feeds/photos_public.gne")
        suspend fun searchPhotos(
            @Query("format") format: String = "json",
            @Query("nojsoncallback") noJsonCallback: Int = 1,
            @Query("tags") tags: String
        ): ResponseModel

}