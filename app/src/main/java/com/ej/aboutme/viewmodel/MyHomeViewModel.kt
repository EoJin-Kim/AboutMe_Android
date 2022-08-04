package com.ej.aboutme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ej.aboutme.api.AboutMeFetchr
import com.ej.aboutme.dto.response.MemberTotalInfoDto
import com.ej.aboutme.dto.response.ResponseDto

class MyHomeViewModel : ViewModel() {
    private val aboutMeFetchr : AboutMeFetchr by lazy { AboutMeFetchr() }
    private var _memberInfo = MutableLiveData<MemberTotalInfoDto>()
    val memberInfo : LiveData<MemberTotalInfoDto>
        get() = _memberInfo

    fun getMemberInfo(memberId: Long) : LiveData<MemberTotalInfoDto>{
        _memberInfo = aboutMeFetchr.getMemberInfo(memberId)
        return memberInfo
    }

    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name
}