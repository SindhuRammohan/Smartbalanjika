package com.template.SmartBalanjika.data.api

import okhttp3.Interceptor
import okhttp3.Response

class SupportInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Accept", "Accept: application/x.school.v1+json")
            .build()
        return chain.proceed(request)
    }

}