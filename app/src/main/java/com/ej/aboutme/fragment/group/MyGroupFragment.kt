package com.ej.aboutme.fragment.group

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.adapter.GroupAdapter
import com.ej.aboutme.databinding.FragmentMyGroupBinding
import com.ej.aboutme.viewmodel.MainViewModel
import com.ej.aboutme.dto.request.CreateGroupDto
import com.ej.aboutme.dto.request.JoinGroupDto
import com.ej.aboutme.dto.response.GroupSummaryDto
import com.ej.aboutme.fragment.dialog.CreateGroupFragmentDialog
import com.ej.aboutme.fragment.dialog.GroupJoinFragmentDialog
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.viewmodel.GroupViweModel


class MyGroupFragment : Fragment() {

    lateinit var myGroupFragmentBinding : FragmentMyGroupBinding

    val act : MainActivity by lazy { activity as MainActivity }
    val viewModel : MainViewModel by lazy { act.mainViewModel }
    val groupViewModel : GroupViweModel by lazy { ViewModelProvider(act).get(GroupViweModel::class.java) }

    lateinit var groupAdapter :GroupAdapter
    val queryPreferences : QueryPreferences by lazy { QueryPreferences()}



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myGroupFragmentBinding = FragmentMyGroupBinding.inflate(LayoutInflater.from(container!!.context),container,false)

        val memberId = queryPreferences.getUserId(requireContext())
        groupViewModel.getGroupSummaryList(memberId)
        val groupSummaryList = groupViewModel.groupSummaryList


        val funGroupVal : (GroupSummaryDto) -> Unit = { groupSummaryDto -> openGroupFragment(groupSummaryDto)}
        groupAdapter = GroupAdapter(funGroupVal)
        groupAdapter.submitList(groupSummaryList.value)
        val groupRecycler = myGroupFragmentBinding.groupRecycler
        groupRecycler.adapter = groupAdapter
        groupRecycler.layoutManager = LinearLayoutManager(requireContext())

        groupSummaryList.observe(viewLifecycleOwner){
            myGroupFragmentBinding.groupLayout.visibility = View.VISIBLE
            groupAdapter.submitList(it)
        }
        myGroupFragmentBinding.addGroupBtn.setOnClickListener {
            createGroupDialog()
//            val createGroupName = groupAddText.editText!!.text.toString()
//            val createTeamDto = CreateTeamDto(memberId,createGroupName)
//            val result = groupViewModel.createGroup(createTeamDto)
//            result.observe(viewLifecycleOwner){
//                groupAdapter.submitList(it)
//            }
        }



        return myGroupFragmentBinding.root
    }
    override fun onResume() {
        super.onResume()
        act.binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_group_add_24)

        val funJoinGroupVal : (JoinGroupDto) -> Unit = { joinGroupDto -> joinGroup(joinGroupDto)}
        act.binding.floatingActionButton.setOnClickListener { btn ->

            val groupJoinDialog = GroupJoinFragmentDialog(funJoinGroupVal)
            groupJoinDialog.show(act.supportFragmentManager,groupJoinDialog.tag)
        }
    }
    private fun createGroupDialog(){
        val funCreateGroupVal : (CreateGroupDto) -> Unit = { createTeamDto -> createGroup(createTeamDto)}
        val dialog = CreateGroupFragmentDialog(funCreateGroupVal)
        dialog.show(
            act.supportFragmentManager,"상세정보!"
        )
    }

    fun createGroup(createGroupDto: CreateGroupDto){
        val result = groupViewModel.createGroup(createGroupDto)
        result.observe(viewLifecycleOwner){
            groupAdapter.submitList(it)
        }
    }
    fun joinGroup(joinGroupDto: JoinGroupDto){
        val result = groupViewModel.joinGroup(joinGroupDto)
        result.observe(viewLifecycleOwner){
            groupAdapter.submitList(it)
        }
    }

    private fun openGroupFragment(groupSummaryDto: GroupSummaryDto){
        groupViewModel.nowGroupId = groupSummaryDto.groupId
        act.setFragment("enter_group")
    }


    companion object {
        fun newInstance(): MyGroupFragment {
            return MyGroupFragment()
        }
    }
}