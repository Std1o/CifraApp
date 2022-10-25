package com.stdio.cifraapp.data

import com.stdio.cifraapp.domain.models.Bank
import retrofit2.Response
import retrofit2.http.POST

interface MainService {

    @POST("bank/available")
    suspend fun getAvailableBanks(): Response<List<Bank>>
}