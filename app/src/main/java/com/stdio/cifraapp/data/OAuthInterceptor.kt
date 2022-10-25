package com.stdio.cifraapp.data

import okhttp3.Interceptor
import okhttp3.Response

class OAuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val finalToken = "Bearer 1"
        request = request.newBuilder()
            .addHeader("Authorization", finalToken)
            .build()
        return chain.proceed(request)
    }
}