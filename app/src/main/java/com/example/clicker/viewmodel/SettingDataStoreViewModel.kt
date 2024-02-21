package com.example.clicker.viewmodel

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.clicker.data.database.Setting
import com.example.clicker.data.repository.SettingRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingDataStoreViewModel @Inject constructor(private val dataRepo : SettingRepository) : ViewModel(){
    var isSwitchOn : MutableLiveData<Setting?> = MutableLiveData()

    init {
        getData()
    }

//    class Factory(val application: Application) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return SettingDataStoreViewModel(application) as T
//        }
//    }

    fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
            dataRepo.getSetting().collect(){
                Log.d(ContentValues.TAG, "getData: ${it}")
                isSwitchOn.postValue(it)
            }
        }
    }
    fun saveData(isSwitchOn: Setting){
        viewModelScope.launch(Dispatchers.IO) {
            dataRepo.saveSWITCH(isSwitchOn)
        }
    }
}