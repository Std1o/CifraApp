package com.stdio.cifraapp.data

class RemoteDataSource(private val mainService: MainService) {

    suspend fun getAvailableBanks() = mainService.getAvailableBanks()
}