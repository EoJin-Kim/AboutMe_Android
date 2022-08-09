package com.ej.aboutme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ej.aboutme.api.AboutMeFetchr
import com.ej.aboutme.dto.request.CreateGroupDto
import com.ej.aboutme.dto.request.JoinGroupDto
import com.ej.aboutme.dto.response.GroupSummaryDto
import com.ej.aboutme.dto.response.GroupTotalDto

class GroupViweModel : ViewModel() {
    private val aboutMeFetchr : AboutMeFetchr by lazy { AboutMeFetchr() }


    private var _groupSummaryList = MutableLiveData<MutableList<GroupSummaryDto>>()
    val groupSummaryList : LiveData<MutableList<GroupSummaryDto>>
        get() = _groupSummaryList

    var nowGroupId :Long = -1L
    var nowGroupMemberId : Long = -1L



    fun getGroupSummaryList(memberId: Long) : LiveData<MutableList<GroupSummaryDto>> {
        _groupSummaryList = aboutMeFetchr.getGroupList(memberId)
        return groupSummaryList
    }

    fun createGroup(createGroupDto : CreateGroupDto) :LiveData<MutableList<GroupSummaryDto>>{

        val result = aboutMeFetchr.createGroup(createGroupDto)
        return result
    }
    fun getTotalGroupInfo(groupId : Long): LiveData<GroupTotalDto>{
        val result = aboutMeFetchr.getTotalGroupInfo(groupId)
        return result
    }

    fun setGroupSummaryList(groupSummaryList : MutableList<GroupSummaryDto>){
        _groupSummaryList.value = groupSummaryList
    }

    fun joinGroup(joinGroupDto: JoinGroupDto) : LiveData<MutableList<GroupSummaryDto>>{
        val result = aboutMeFetchr.joinGroup(joinGroupDto)
        return result
    }
}