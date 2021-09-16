package com.exercise.nasapictures.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.exercise.nasapictures.baseclasses.BaseViewModel
import com.exercise.nasapictures.common.ViewClickState
import com.exercise.nasapictures.common.readDataFromAsset
import com.exercise.nasapictures.common.toDate
import com.exercise.nasapictures.modelclasses.NasaPhotoInfoPojo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PhotosViewModel(private val application: Application) : BaseViewModel() {
    var photosList = mutableListOf<NasaPhotoInfoPojo>()
    var selectedItem: Int = 0
    private val fileName = "data.json"
    val viewState = MutableStateFlow<ViewClickState>(ViewClickState.StateNone)

    init {
        preparePhotoList()
    }

    /**
     * Parse json data into photos list
     */
    private fun preparePhotoList() {
        viewModelScope.launch {
            val photoCollectionType = object : TypeToken<Collection<NasaPhotoInfoPojo>>() {}.type
            photosList = Gson().fromJson(application.assets.readDataFromAsset(fileName), photoCollectionType)
            photosList.sortBy { it.date.toDate() }
        }
    }

    fun onPreviousButtonClick() {
        viewState.value = ViewClickState.ButtonPreviousClick
        viewState.replayCache
    }

    fun onNextButtonClick() {
        viewState.value = ViewClickState.ButtonNextClick
    }

    fun onBackClick() {
        viewState.value = ViewClickState.ImageViewBackClick
    }
}