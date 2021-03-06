package com.template.SmartBalanjika.data.helper

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.template.SmartBalanjika.data.model.Home
import com.template.SmartBalanjika.data.model.User
import com.template.SmartBalanjika.di.PreferenceInfo
import com.template.SmartBalanjika.utils.AppConstants
import javax.inject.Inject


class AppPreferenceHelperImpl @Inject constructor(

    context: Context,
    gson: Gson,
    @PreferenceInfo private val preferenceName: String

) : PreferencesHelper {

    private var mPrefs: SharedPreferences? = null
    private var gsonBuilder: Gson? = null

    init {
        mPrefs = context.getSharedPreferences(
            preferenceName,
            Context.MODE_PRIVATE
        )
        gsonBuilder = gson
    }


    override fun setCurrentUserLoggedInMode(loggedInMode: AppConstants.LoggedInMode) {

        mPrefs!!.edit().putInt(AppConstants.PREF_KEY_USER_LOGGED_IN_MODE, loggedInMode.type).apply()

    }

    override fun getCurrentUserLoggedInMode(): Int {
        return mPrefs!!.getInt(
            AppConstants.PREF_KEY_USER_LOGGED_IN_MODE,
            AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type
        )
    }

    override fun getUserLoginDetails(): User? {

        val userDetails: String? = mPrefs!!.getString(AppConstants.PREF_KEY_CURRENT_USER_DETAILS, null);

        return gsonBuilder!!.fromJson(userDetails,User::class.java)
    }

    override fun setUserLoginDetails(user: User?) {
        mPrefs!!.edit().putString(AppConstants.PREF_KEY_CURRENT_USER_DETAILS, gsonBuilder!!.toJson(user)).apply()
    }

    override fun getAccessToken(): String? {
        return mPrefs!!.getString(AppConstants.PREF_KEY_ACCESS_TOKEN,null)
    }

    override fun setAccessToken(accessToken: String?) {
        mPrefs!!.edit().putString(AppConstants.PREF_KEY_ACCESS_TOKEN, accessToken).apply()

    }

    override fun getHomeFeeds(): Home? {

        val home: String? = mPrefs!!.getString(AppConstants.PREF_KEY_HOME_FEEDS, null);

        return gsonBuilder!!.fromJson(home,Home::class.java)

    }

    override fun setHomeFeeds(home: Home?) {
        mPrefs!!.edit().putString(AppConstants.PREF_KEY_HOME_FEEDS, gsonBuilder!!.toJson(home)).apply()

    }


}