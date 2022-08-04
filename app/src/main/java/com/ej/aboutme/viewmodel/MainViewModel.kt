package com.ej.aboutme.viewmodel

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ej.aboutme.data.repository.MemberRepository

class MainViewModel(private val memberRepostiory : MemberRepository) : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name

    fun setName(name: String){
        _name.value = name
    }
    fun loginCheck(){

    }

}