package com.ej.aboutme.data

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
}