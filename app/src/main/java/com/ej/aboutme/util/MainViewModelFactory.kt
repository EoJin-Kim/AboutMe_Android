package com.ej.aboutme.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ej.aboutme.viewmodel.MainViewModel
import com.ej.aboutme.data.repository.MemberRepository

class MainViewModelFactory(
    private val repository: MemberRepository
    ) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(repository) as T
        }
        return super.create(modelClass)
    }
}