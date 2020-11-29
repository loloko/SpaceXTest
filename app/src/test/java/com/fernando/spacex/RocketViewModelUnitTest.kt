package com.fernando.spacex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.fernando.spacex.helpers.ResultResource
import com.fernando.spacex.model.RocketModel
import com.fernando.spacex.repository.RocketRepository
import com.fernando.spacex.viewmodel.RocketListViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
class RocketViewModelUnitTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var repository: RocketRepository

    private val mockedObserver = createObserver()

    private lateinit var viewModel: RocketListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = RocketListViewModel()

        viewModel.rocketRepository = repository

        viewModel.rocketResultObserver().observeForever(mockedObserver)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `given success state, when getRockets called`() {

        //Given
        val resource = emptyList<RocketModel>()
        coEvery { repository.getRockets() } returns resource

        //When
        runBlocking { viewModel.getRockets() }

        //Then
        coVerifyOrder {
            mockedObserver.onChanged(ResultResource.Loading)
            mockedObserver.onChanged(ResultResource.Success(resource))
        }
    }

    @Test
    fun `given error 404 not found, when getRockets called`() {

        //Given
        val bodyError = Response.error<ResponseBody>(404, ResponseBody.create(MediaType.parse("type"), "test"))
        coEvery { repository.getRockets() } throws HttpException(bodyError)

        //When
        runBlocking { viewModel.getRockets() }

        //Then
        coVerifyOrder {
            mockedObserver.onChanged(ResultResource.Loading)

            mockedObserver.onChanged(ResultResource.NotFound)
        }
    }

    @Test
    fun `given error 400, should get fetch error, when getRockets called`() {

        //Given
        val bodyError = Response.error<ResponseBody>(400, ResponseBody.create(MediaType.parse("type"), "test"))
        coEvery { repository.getRockets() } throws HttpException(bodyError)

        //When
        runBlocking { viewModel.getRockets() }

        //Then
        coVerifyOrder {
            mockedObserver.onChanged(ResultResource.Loading)

            mockedObserver.onChanged(ResultResource.Error(R.string.fetch_error))
        }
    }

    @Test
    fun `given error state, when getRockets called`() {

        //Given
        val resource = ResultResource.Error(R.string.fetch_error)
        coEvery { repository.getRockets() } throws Exception("this is a test")

        //When
        runBlocking { viewModel.getRockets() }

        //Then
        coVerifyOrder {
            mockedObserver.onChanged(ResultResource.Loading)

            mockedObserver.onChanged(resource)

            val state = viewModel.rocketResultObserver().value
            assertThat((state as ResultResource.Error).msg).isEqualTo(resource.msg)
        }
    }

    private fun createObserver(): Observer<ResultResource<List<RocketModel>>> =
        spyk(Observer { })

}