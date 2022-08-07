package com.ej.aboutme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ej.aboutme.api.AboutMeFetchr
import com.ej.aboutme.dto.request.CreateTeamDto
import com.ej.aboutme.dto.response.GroupSummaryDto
import com.ej.aboutme.dto.response.GroupTotalDto
import com.ej.aboutme.dto.response.MemberTotalInfoDto

class GroupViweModel : ViewModel() {
    private val aboutMeFetchr : AboutMeFetchr by lazy { AboutMeFetchr() }


    private var _groupSummaryList = MutableLiveData<MutableList<GroupSummaryDto>>()
    val groupSummaryList : LiveData<MutableList<GroupSummaryDto>>
        get() = _groupSummaryList

    var nowGroupId :Long = -1L



    fun getGroupSummaryList(memberId: Long) : LiveData<MutableList<GroupSummaryDto>> {
        _groupSummaryList = aboutMeFetchr.getGroupList(memberId)
        return groupSummaryList
    }

    fun createGroup(createTeamDto : CreateTeamDto) :LiveData<MutableList<GroupSummaryDto>>{

        val result = aboutMeFetchr.createGroup(createTeamDto)
        return result
    }
    fun getTotalGroupInfo(groupId : Long): LiveData<GroupTotalDto>{
        val result = aboutMeFetchr.getTotalGroupInfo(groupId)
        return result
    }

    fun setGroupSummaryList(groupSummaryList : MutableList<GroupSummaryDto>){
        _groupSummaryList.value = groupSummaryList
    }
}