package com.ej.aboutme.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ej.aboutme.api.AboutMeFetchr
import com.ej.aboutme.dto.request.MemberInfoContentDto
import com.ej.aboutme.dto.request.MemberUpdateDto
import com.ej.aboutme.dto.response.MemberInfoDto
import com.ej.aboutme.dto.response.MemberTotalInfoDto
import kotlinx.coroutines.launch
import java.io.File

class MemberViewModel : ViewModel() {
    private val aboutMeFetchr : AboutMeFetchr by lazy { AboutMeFetchr()
    }
    private var _memberTotalInfo = MutableLiveData<MemberTotalInfoDto>()
    val memberTotalInfo : LiveData<MemberTotalInfoDto>
        get() = _memberTotalInfo

    private val _memberInfo =MutableLiveData<List<MemberInfoDto>>()
    val memberInfo : LiveData<List<MemberInfoDto>>
        get() = _memberInfo



    fun getMemberTotalInfo(memberId: Long) : LiveData<MemberTotalInfoDto>{
        viewModelScope.launch {
            _memberTotalInfo = aboutMeFetchr.getMemberInfo(memberId)
        }
        return memberTotalInfo
    }

    fun updateMember(memberId : Long, memberUpdateDto: MemberUpdateDto,image: File?) : LiveData<String>{
        val result = aboutMeFetchr.updateMember(memberId,memberUpdateDto,image)
        return result
    }

    fun updateMemberInfo(memberInfoId:Long, memberInfoContentDto: MemberInfoContentDto) : LiveData<List<MemberInfoDto>>{
        val result = aboutMeFetchr.updateMemberInfo(memberInfoId,memberInfoContentDto)
        return result
    }
    fun setMemberInfo(memberInfoList : List<MemberInfoDto>){
        _memberInfo.value = memberInfoList
    }

    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name


}