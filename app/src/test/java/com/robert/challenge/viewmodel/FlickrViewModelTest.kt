package com.robert.challenge.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.robert.challenge.data.model.MediaModel
import com.robert.challenge.data.model.PhotoModel
import com.robert.challenge.data.remote.ApiResult
import com.robert.challenge.data.repository.RepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MyViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private var testDispatcher = StandardTestDispatcher()

    private lateinit var repository: RepositoryImpl
    private lateinit var viewModel: FlickrViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = FlickrViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun searchPhotos() = runTest {
        // Given
        val expectedPhotos = ApiResult.Success(
            listOf(
                PhotoModel(
                    author = "John Doe",
                    authorId = "12345",
                    dateTaken = "2024-12-11T10:15:30Z",
                    description = "A beautiful photo of the sunrise.",
                    link = "http://example.com/photo",
                    media = MediaModel(m = "http://example.com/photo.jpg"),
                    published = "2024-12-11T12:00:00Z",
                    tags = "sunrise, nature, photo",
                    title = "Sunrise"
                )
            )
        )

        // When
        coEvery {
            repository.searchPhotos("nature")
        } returns expectedPhotos
        viewModel.searchPhotos("nature")
        advanceUntilIdle()

        // Then
        assertEquals(viewModel.photos.value, expectedPhotos)
    }

    @Test
    fun `search failed`() = runTest {
        // Given
        val expectedPhotos = ApiResult.Error(
            Exception("Failed to fetch photos")
        )

        // When
        coEvery {
            repository.searchPhotos("nature")
        } returns expectedPhotos
        viewModel.searchPhotos("nature")
        advanceUntilIdle()

        // Then
        assertEquals(viewModel.photos.value, expectedPhotos)
    }
}
