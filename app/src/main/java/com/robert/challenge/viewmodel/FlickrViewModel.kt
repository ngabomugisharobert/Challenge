package com.robert.challenge.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robert.challenge.data.model.PhotoModel
import com.robert.challenge.data.remote.ApiResult
import com.robert.challenge.data.repository.FlickrRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.State
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlickrViewModel @Inject constructor(private val flickrRepository: FlickrRepository) :
    ViewModel() {


    private val _photos = MutableStateFlow<ApiResult<List<PhotoModel>>>(ApiResult.Idle)
    val photos: StateFlow<ApiResult<List<PhotoModel>>> = _photos

    private val _selectedPhoto = mutableStateOf<PhotoModel?>(null)
    val selectedPhoto: State<PhotoModel?> = _selectedPhoto

    fun searchPhotos(tags: String) {
//        remove space from tags
        val tags = tags.replace(" ", "")
        _photos.value = ApiResult.Loading
        viewModelScope.launch {
            try {
                _photos.value = flickrRepository.searchPhotos(tags)
            } catch (e: Exception) {
                _photos.value = ApiResult.Error(e)
            }
        }
    }

    // Function to set the selected photo
    fun selectPhoto(photo: PhotoModel) {
        _selectedPhoto.value = photo
    }

    // Function to fetch photos
    private fun fetchPhotos() {
        _photos.value = ApiResult.Loading ?: ApiResult.Idle
        viewModelScope.launch {
            try {
                _photos.value = flickrRepository.fetchPhotos() ?: ApiResult.Error(Exception("Null Data Returned"))
            } catch (e: Exception) {
                _photos.value = ApiResult.Error(e)
            }
        }
    }

    // Clear the selected photo if necessary
    fun clearSelectedPhoto() {
        _selectedPhoto.value = null
    }
}