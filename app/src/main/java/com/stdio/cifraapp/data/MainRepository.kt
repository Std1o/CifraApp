package com.stdio.cifraapp.data

import kotlinx.coroutines.flow.flow

class MainRepository(private val remoteDataSource: RemoteDataSource) : BaseRepository() {

    suspend fun getAvailableBanks() =
        flow { emit(apiCall { remoteDataSource.getAvailableBanks() }) }
}