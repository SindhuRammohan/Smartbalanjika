package com.template.SmartBalanjika.data.helper

import com.template.SmartBalanjika.data.model.Home
import com.template.SmartBalanjika.data.model.User
import com.template.SmartBalanjika.utils.AppConstants

interface PreferencesHelper {

    fun setCurrentUserLoggedInMode(loggedInMode: AppConstants.LoggedInMode)
    fun getCurrentUserLoggedInMode(): Int?

    fun getUserLoginDetails(): User?
    fun setUserLoginDetails(user: User?)

    fun getAccessToken(): String?
    fun setAccessToken(accessToken: String?)

    fun getHomeFeeds(): Home?
    fun setHomeFeeds(home: Home?)

}