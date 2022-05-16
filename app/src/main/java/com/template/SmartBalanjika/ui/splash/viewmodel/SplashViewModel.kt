package com.template.SmartBalanjika.ui.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.SmartBalanjika.data.helper.PreferencesHelper
import com.template.SmartBalanjika.utils.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferencesHelper: PreferencesHelper
) : ViewModel() {

    private val TAG = SplashViewModel::class.qualifiedName

    var isLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    fun isUserLoggedIn(): LiveData<Boolean> {
        return isLoggedIn
    }

    fun checkLoggedInMode() {
        viewModelScope.launch {
            if (preferencesHelper.getCurrentUserLoggedInMode()!! == AppConstants.LoggedInMode.LOGGED_IN_MODE_SERVER.type) {
                isLoggedIn.postValue(true)
            } else {
                isLoggedIn.postValue(false)
            }
        }
    }


}