package com.ej.aboutme.fragment.group

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
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
import com.ej.aboutme.viewmodel.MemberViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyGroupFragment : Fragment() {

    lateinit var binding : FragmentMyGroupBinding

    val act : MainActivity by lazy { activity as MainActivity }
    val viewModel : MainViewModel by lazy { act.mainViewModel }
    private val groupViewModel: GroupViweModel by activityViewModels()
    private val memberViewModel: MemberViewModel by activityViewModels()

    val queryPreferences : QueryPreferences by lazy { QueryPreferences()}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyGroupBinding.inflate(LayoutInflater.from(container!!.context),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        binding.addGroupBtn.setOnClickListener {
            createGroupDialog()
        }
        binding.joinGroupBtn.setOnClickListener {
            joinGroupDialog()
        }
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
    private fun joinGroupDialog() {

    }

    private fun setRecycler() {
        val memberId = queryPreferences.getUserId(requireContext())
        val funGroupVal: (GroupSummaryDto) -> Unit = { groupSummaryDto -> openGroupFragment(groupSummaryDto) }
        val groupAdapter = GroupAdapter(funGroupVal)
        val groupRecycler = binding.groupRecycler
        groupRecycler.adapter = groupAdapter
        groupRecycler.layoutManager = LinearLayoutManager(requireContext())

        groupViewModel.groupSummaryList.observe(viewLifecycleOwner) {
            binding.groupLayout.visibility = View.VISIBLE
            groupAdapter.submitList(it)
        }
        groupViewModel.getGroupSummaryList(memberId)
    }

    private fun createGroupDialog(){
        val funCreateGroupVal : (CreateGroupDto) -> Unit = { createTeamDto -> createGroup(createTeamDto)}
        val dialog = CreateGroupFragmentDialog(funCreateGroupVal)
        dialog.show(
            act.supportFragmentManager,"상세정보!"
        )
    }

    fun createGroup(createGroupDto: CreateGroupDto){
        groupViewModel.createGroup(createGroupDto)
    }
    fun joinGroup(joinGroupDto: JoinGroupDto){
        groupViewModel.joinGroup(joinGroupDto)
    }

    private fun openGroupFragment(groupSummaryDto: GroupSummaryDto){
        groupViewModel.nowGroupId = groupSummaryDto.group_id
        act.setFragment(OpenGroupFragment.TAG)
    }


    companion object {
        val TAG = "MyGroupFragment"
        fun newInstance(): MyGroupFragment {
            return MyGroupFragment()
        }
    }
}