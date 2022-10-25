package com.stdio.cifraapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.stdio.cifraapp.data.MainRepository
import com.stdio.cifraapp.domain.models.Bank
import kotlinx.coroutines.launch

class BankListViewModel (private val repository: MainRepository) : BaseViewModel<List<Bank>>() {

    init {
        getAvailableBanks()
    }

    private fun getAvailableBanks() {
        viewModelScope.launch {
            launchRequest(repository.getAvailableBanks())
        }
    }
}