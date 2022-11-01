package com.ej.aboutme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ej.aboutme.api.AboutMeFetchr
import com.ej.aboutme.dto.request.LoginDto
import com.ej.aboutme.dto.request.MemberInfoContentDto
import com.ej.aboutme.dto.request.MemberUpdateDto
import com.ej.aboutme.dto.request.SignupDto
import com.ej.aboutme.dto.response.LoginResultDto
import com.ej.aboutme.dto.response.MemberInfoDto
import com.ej.aboutme.dto.response.MemberTotalInfoDto
import com.ej.aboutme.dto.response.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val aboutMeFetchr: AboutMeFetchr
): ViewModel() {

    val memberTotalInfo = MutableLiveData<MemberTotalInfoDto>()
    val loginResult = MutableLiveData<LoginResultDto>()
    val memberCardInfoList = MutableLiveData<List<MemberInfoDto>>()
    val updateMemberCheck = MutableLiveData<String>()
    val resonseStatus = MutableLiveData<ResponseStatus>()

    private val _memberInfo =MutableLiveData<List<MemberInfoDto>>()
    val memberInfo : LiveData<List<MemberInfoDto>>
        get() = _memberInfo


    fun loginMember(loginDto: LoginDto){
        viewModelScope.launch{
            loginResult.value = aboutMeFetchr.login(loginDto).value!!.response!!
        }

    }

    fun getMemberTotalInfo(memberId: Long){
        viewModelScope.launch {
            memberTotalInfo.value = aboutMeFetchr.getMemberInfo(memberId).value
        }
    }

    fun updateMember(memberId : Long, memberUpdateDto: MemberUpdateDto,image: File?) {
        viewModelScope.launch {
            updateMemberCheck.value = aboutMeFetchr.updateMember(memberId,memberUpdateDto,image).value
        }
    }

    fun updateMemberInfo(memberInfoId:Long, memberInfoContentDto: MemberInfoContentDto){
        viewModelScope.launch {
            memberCardInfoList.value = aboutMeFetchr.updateMemberInfo(memberInfoId,memberInfoContentDto).value
        }
    }
    fun setMemberInfo(memberInfoList : List<MemberInfoDto>){
        _memberInfo.value = memberInfoList
    }

    fun signUp(signupDto: SignupDto) {
        viewModelScope.launch {
            resonseStatus.value = aboutMeFetchr.signup(signupDto).value
        }
    }

    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name


}