package com.stdio.cifraapp.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.stdio.cifraapp.R
import com.stdio.cifraapp.common.subscribeInUI
import com.stdio.cifraapp.common.viewBinding
import com.stdio.cifraapp.databinding.FragmentBankListBinding
import com.stdio.cifraapp.domain.models.Bank
import com.stdio.cifraapp.presentation.viewmodel.BankListViewModel
import student.testing.system.presentation.ui.adapters.BanksAdapter


class BankListFragment : Fragment(R.layout.fragment_bank_list) {

    private val viewModel by viewModel<BankListViewModel>()
    private val binding by viewBinding(FragmentBankListBinding::bind)
    lateinit var adapter: BanksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BanksAdapter(object : BanksAdapter.ClickListener {
            override fun onClick(bank: Bank) {
                // TODO
            }
        })
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.uiState.subscribeInUI(this, binding.progressBar) {
            adapter.setDataList(it)
        }
    }
}