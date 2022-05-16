package com.template.SmartBalanjika.di.module

import android.content.Context
import androidx.annotation.NonNull
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.template.SmartBalanjika.BuildConfig
import com.template.SmartBalanjika.data.api.*
import com.template.SmartBalanjika.data.helper.AppPreferenceHelperImpl
import com.template.SmartBalanjika.data.helper.PreferencesHelper
import com.template.SmartBalanjika.di.PreferenceInfo
import com.template.SmartBalanjika.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule() {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }


    @Provides
    fun providesBaseUrl() = BuildConfig.BASE_URL

    //    @Provides
//    @Singleton
//    fun provideSupportAuthenticator(): SupportAuthenticator {
//        return SupportAuthenticator()
//    }

    @Provides
    @Singleton
    fun provideOkHttpClient(preferencesHelper: AppPreferenceHelperImpl): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        val supportInterceptor = SupportInterceptor()
        val supportAuthenticator = SupportAuthenticator(preferencesHelper)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        if (BuildConfig.DEBUG) {
            return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .followRedirects(true)
                .followSslRedirects(true)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(supportInterceptor)
                .authenticator(supportAuthenticator)
                .build()
        } else {
            return OkHttpClient
                .Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .followRedirects(true)
                .followSslRedirects(true)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(supportInterceptor)
                .authenticator(supportAuthenticator)
                .build()
        }
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)



    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper


    @Provides
    @PreferenceInfo
    fun providePreferenceName() = AppConstants.PREF_NAME


    @Provides
    @Singleton
    fun providePreferencesHelper(preferencesHelper: AppPreferenceHelperImpl): PreferencesHelper =
        preferencesHelper


    @Provides
    @Singleton
    @NonNull
    fun provideGson(): Gson {
        return Gson()
    }

}
