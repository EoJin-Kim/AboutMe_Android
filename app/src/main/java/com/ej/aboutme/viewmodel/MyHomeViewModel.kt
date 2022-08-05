package com.ej.aboutme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ej.aboutme.api.AboutMeFetchr
import com.ej.aboutme.dto.request.MemberUpdateDto
import com.ej.aboutme.dto.response.MemberTotalInfoDto

class MyHomeViewModel : ViewModel() {
    private val aboutMeFetchr : AboutMeFetchr by lazy { AboutMeFetchr() }
    private var _memberTotalInfo = MutableLiveData<MemberTotalInfoDto>()
    val memberTotalInfo : LiveData<MemberTotalInfoDto>
        get() = _memberTotalInfo

    fun getMemberTotalInfo(memberId: Long) : LiveData<MemberTotalInfoDto>{
        _memberTotalInfo = aboutMeFetchr.getMemberInfo(memberId)
        return memberTotalInfo
    }

    fun updateMember(memberId : Long, memberUpdateDto: MemberUpdateDto) : LiveData<String>{
        val result = aboutMeFetchr.updateMember(memberId,memberUpdateDto)
        return result
    }

    fun updateMemberInfo(memberInfoId:Long) : LiveData<String>{
        val result = aboutMeFetchr.updateMemberInfo(memberInfoId)
        return result
    }

    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name


}