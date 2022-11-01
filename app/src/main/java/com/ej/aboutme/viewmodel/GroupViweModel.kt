package com.ej.aboutme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ej.aboutme.api.AboutMeFetchr
import com.ej.aboutme.dto.request.CreateGroupDto
import com.ej.aboutme.dto.request.JoinGroupDto
import com.ej.aboutme.dto.response.GroupSummaryDto
import com.ej.aboutme.dto.response.GroupTotalDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViweModel @Inject constructor(
    private val aboutMeFetchr: AboutMeFetchr
) : ViewModel() {



    val groupSummaryList = MutableLiveData<MutableList<GroupSummaryDto>>()
    val groupTotal = MutableLiveData<GroupTotalDto>()

    var nowGroupId :Long = -1L
    var nowGroupMemberId : Long = -1L



    fun getGroupSummaryList(memberId: Long){
        viewModelScope.launch {
            groupSummaryList.value = aboutMeFetchr.getGroupList(memberId).value
        }
    }

    fun createGroup(createGroupDto : CreateGroupDto) {
        viewModelScope.launch {
            groupSummaryList.value = aboutMeFetchr.createGroup(createGroupDto).value
        }

    }
    fun getTotalGroupInfo(groupId : Long){
        viewModelScope.launch {
            groupTotal.value = aboutMeFetchr.getTotalGroupInfo(groupId).value
        }
    }



    fun joinGroup(joinGroupDto: JoinGroupDto){
        viewModelScope.launch {
            groupSummaryList.value = aboutMeFetchr.joinGroup(joinGroupDto).value
        }
    }
}