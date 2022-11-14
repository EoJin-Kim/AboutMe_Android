package com.ej.aboutme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ej.aboutme.api.AboutMeApi
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
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val aboutMeApi: AboutMeApi
): ViewModel() {

    private val _memberTotalInfo = MutableLiveData<MemberTotalInfoDto>()
    val memberTotalInfo : LiveData<MemberTotalInfoDto>
        get() = _memberTotalInfo

    private val _loginResult = MutableLiveData<LoginResultDto>()
    val loginResult : LiveData<LoginResultDto>
        get() = _loginResult

    private val _memberCardInfoList = MutableLiveData<List<MemberInfoDto>>()
    val memberCardInfoList : LiveData<List<MemberInfoDto>>
        get() = _memberCardInfoList

    private val _updateMemberCheck = MutableLiveData<String>()
    val updateMemberCheck: LiveData<String>
        get() = _updateMemberCheck

    private val _signupResult = MutableLiveData<String>()
    val signupResult : LiveData<String>
        get() = _signupResult

    private val _memberInfo =MutableLiveData<List<MemberInfoDto>>()
    val memberInfo : LiveData<List<MemberInfoDto>>
        get() = _memberInfo


    fun loginMember(loginDto: LoginDto){
        viewModelScope.launch{
            _loginResult.value = aboutMeApi.login(loginDto).response!!
        }

    }

    fun getMemberTotalInfo(memberId: Long){
        viewModelScope.launch {
            _memberTotalInfo.value = aboutMeApi.getMemberInfo(memberId).response!!
        }
    }

    fun updateMember(memberId : Long, memberUpdateDto: MemberUpdateDto,image: File?) {
        viewModelScope.launch {
            var filePart : MultipartBody.Part? = null

            if(image!=null){
                val imageBody = RequestBody.create(MediaType.parse("image/jpeg"), image);
                filePart = MultipartBody.Part.createFormData("memberImage",image?.name,imageBody)
            }

            val nameBody = RequestBody.create(MediaType.parse("text/plain"),memberUpdateDto.name)
            val jobBody = RequestBody.create(MediaType.parse("text/plain"),memberUpdateDto.job)
            val phoneBody = RequestBody.create(MediaType.parse("text/plain"),memberUpdateDto.phone)
            val contentBody = RequestBody.create(MediaType.parse("text/plain"),memberUpdateDto.content)

            val requestMap = HashMap<String,RequestBody>()
            requestMap.put("name",nameBody)
            requestMap.put("job",jobBody)
            requestMap.put("phone",phoneBody)
            requestMap.put("content",contentBody)

            val tagBody =  ArrayList<MultipartBody.Part>()
            for (tag in memberUpdateDto.tag) {
                tagBody.add(MultipartBody.Part.createFormData("tag",tag))
            }
            _updateMemberCheck.value = aboutMeApi.updateMember(
                memberId,
                filePart,
                requestMap,
                tagBody
            ).response!!
        }
    }

    fun updateMemberInfo(memberInfoId:Long, memberInfoContentDto: MemberInfoContentDto){
        viewModelScope.launch {
            _memberCardInfoList.value = aboutMeApi.updateMemberInfo(memberInfoId,memberInfoContentDto).response!!
        }
    }
    fun setMemberInfo(memberInfoList : List<MemberInfoDto>){
        _memberInfo.value = memberInfoList
    }

    fun signUp(signupDto: SignupDto) {
        viewModelScope.launch {
            _signupResult.value = aboutMeApi.signup(signupDto).response!!
        }
    }

    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name

}