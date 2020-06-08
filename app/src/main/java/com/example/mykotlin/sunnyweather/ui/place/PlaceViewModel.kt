package com.example.mykotlin.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mykotlin.sunnyweather.logic.Repository
import com.example.mykotlin.sunnyweather.logic.model.Place

/**
 * @author yang on 2020/6/7
 */
class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData =
        Transformations.switchMap(searchLiveData) { query -> Repository.searchPlaces(query) }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

}