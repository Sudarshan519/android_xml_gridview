package com.example.momoclone.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AudioBookViewModel : ViewModel() {
    var selectedItem= MutableLiveData<Int>(0)
    var loading= mutableStateOf(false)


}