package com.ej.aboutme.fragment.group

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.adapter.GroupMemberAdapter
import com.ej.aboutme.databinding.FragmentMyGroupBinding
import com.ej.aboutme.databinding.FragmentOpenGroupBinding
import com.ej.aboutme.dto.response.MemberInfoDto
import com.ej.aboutme.dto.response.MemberSummaryDto
import com.ej.aboutme.fragment.navi.MyHomeFragment
import com.ej.aboutme.viewmodel.GroupViweModel

private const val ARG_GROUP_ID = "group_id"
class OpenGroupFragment : Fragment() {

    val act : MainActivity by lazy { activity as MainActivity }
    val groupViewModel : GroupViweModel by lazy { ViewModelProvider(act).get(GroupViweModel::class.java) }

    lateinit var openGroupFragment : FragmentOpenGroupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        openGroupFragment = FragmentOpenGroupBinding.inflate(inflater)

//        val groupIdText = openGroupFragment.groupIdText
        val groupId = groupViewModel.nowGroupId
//        groupIdText.text = "$groupId"
        val totalGroupInfo = groupViewModel.getTotalGroupInfo(groupId)
        val groupRecycler = openGroupFragment.groupMemberRecycler
        groupRecycler.layoutManager = LinearLayoutManager(act,LinearLayoutManager.HORIZONTAL,false)
        totalGroupInfo.observe(viewLifecycleOwner){
            openGroupFragment.openGroupName.text = it.groupName
            openGroupFragment.openGroupSummary.text = it.groupSummary
            openGroupFragment.openMemberCount.text = "총 ${it.count}명"

            val funGroupMemberVal : (MemberSummaryDto) -> Unit = { memberSummaryDto -> groupMemberOpenFragemnt(memberSummaryDto)}
            val groupMemberAdapter =GroupMemberAdapter(funGroupMemberVal)
            groupRecycler.adapter = groupMemberAdapter
            groupMemberAdapter.submitList(it.memberSummary)
            openGroupFragment.groupLayout.visibility = View.VISIBLE
        }
        return openGroupFragment.root
    }

    private fun groupMemberOpenFragemnt(memberSummaryDto: MemberSummaryDto){
        groupViewModel.nowGroupMemberId = memberSummaryDto.id
        act.setFragment("open_member")
    }
    override fun onResume() {
        super.onResume()
        act.binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_arrow_back_24)
        act.binding.floatingActionButton.setOnClickListener { btn ->
//            val a = act.supportFragmentManager.backStackEntryCount
//            val b = act.supportFragmentManager.fragments.size
//            Log.d("Moon","B=$a")
//            Log.d("Moon","C=$b")



            act.supportFragmentManager.popBackStack()
//            act.supportFragmentManager.beginTransaction().remove(this).commit()
            // backstack 설정

        }
    }

    companion object {
        fun newInstance(): OpenGroupFragment {
            return OpenGroupFragment()
        }
    }
}