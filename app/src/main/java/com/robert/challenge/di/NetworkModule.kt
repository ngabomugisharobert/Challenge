package com.robert.challenge.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSyntaxException
import com.robert.challenge.api.FlickrAPIService
import com.robert.challenge.data.repository.FlickrRepository
import com.robert.challenge.data.repository.RepositoryImpl
import com.robert.challenge.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

        @Provides
        fun getGson() : Gson? {
            return GsonBuilder()
                .registerTypeAdapter(Int::class.java, object : JsonDeserializer<Int> {
                    override fun deserialize(
                        json: JsonElement,
                        typeOfT: Type,
                        context: JsonDeserializationContext
                    ): Int {
                        return try {
                            json.asInt
                        } catch (e: NumberFormatException) {
                            0 // default value
                        } catch (e: JsonSyntaxException) {
                            0 // default value
                        }
                    }
                })
                .create()
        }


    @Provides
    fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json;charset=utf-8")
                    .header("Accept", "application/json")
                    .method(original.method, original.body)

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun getAPIService(retrofit: Retrofit): FlickrAPIService {
        return retrofit.create(FlickrAPIService::class.java)
    }

    @Provides
    fun getRepository(apiService: FlickrAPIService): FlickrRepository {
        return RepositoryImpl(apiService)

    }
}