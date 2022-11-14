package com.ej.aboutme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ej.aboutme.api.AboutMeApi
import com.ej.aboutme.dto.request.CreateGroupDto
import com.ej.aboutme.dto.request.JoinGroupDto
import com.ej.aboutme.dto.response.GroupSummaryDto
import com.ej.aboutme.dto.response.GroupTotalDto
import com.ej.aboutme.dto.response.MemberTotalInfoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViweModel @Inject constructor(
    private val aboutMeApi: AboutMeApi
) : ViewModel() {

    private val _groupSummaryList = MutableLiveData<MutableList<GroupSummaryDto>>()
    val groupSummaryList : LiveData<MutableList<GroupSummaryDto>>
        get() = _groupSummaryList

    private val _groupTotal = MutableLiveData<GroupTotalDto>()
    val groupTotal : LiveData<GroupTotalDto>
        get() = _groupTotal

    private val _groupMemberTotalInfo = MutableLiveData<MemberTotalInfoDto>()
    val groupMemberTotalInfo : LiveData<MemberTotalInfoDto>
        get() = _groupMemberTotalInfo

    var nowGroupId :Long = -1L
    var nowGroupMemberId : Long = -1L

    fun getGroupSummaryList(memberId: Long){
        viewModelScope.launch {
            _groupSummaryList.value = aboutMeApi.getGroupListInfo(memberId).response!!
        }
    }

    fun createGroup(createGroupDto : CreateGroupDto) {
        viewModelScope.launch {
            _groupSummaryList.value = aboutMeApi.createGroup(createGroupDto).response!!
        }
    }

    fun getTotalGroupInfo(groupId : Long){
        viewModelScope.launch {
            _groupTotal.value = aboutMeApi.getTotalGroupInfo(groupId).response!!
        }
    }

    fun joinGroup(joinGroupDto: JoinGroupDto){
        viewModelScope.launch {
            _groupSummaryList.value = aboutMeApi.joinGroup(joinGroupDto).response!!
        }
    }

    fun getGroupMemberTotalInfo(){
        viewModelScope.launch {
            _groupMemberTotalInfo.value = aboutMeApi.getMemberInfo(nowGroupMemberId).response!!
        }
    }
}