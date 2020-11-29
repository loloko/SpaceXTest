package com.fernando.spacex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.spacex.R
import com.fernando.spacex.helpers.ResultResource
import com.fernando.spacex.model.RocketModel
import com.fernando.spacex.repository.RocketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class RocketListViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var rocketRepository: RocketRepository

    private val rocketResult = MutableLiveData<ResultResource<List<RocketModel>>>()

    fun rocketResultObserver(): LiveData<ResultResource<List<RocketModel>>> = rocketResult

    fun getRockets() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                rocketResult.postValue(ResultResource.Loading)

                rocketResult.postValue(ResultResource.Success(rocketRepository.getRockets()))

            } catch (exception: HttpException) {
                if (exception.code() == 404)
                    rocketResult.postValue(ResultResource.NotFound)
                else
                    rocketResult.postValue(ResultResource.Error(R.string.fetch_error))

            } catch (exception: Exception) {
                rocketResult.postValue(ResultResource.Error(R.string.fetch_error))
            }
        }
    }

}


