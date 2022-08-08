package com.ej.aboutme.fragment.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ej.aboutme.MainActivity
import com.ej.aboutme.databinding.FragmentMemberFirstBinding
import com.ej.aboutme.fragment.navi.MyHomeEditFragment
import com.ej.aboutme.viewmodel.GroupViweModel
import com.ej.aboutme.viewmodel.MemberViewModel
import com.google.android.material.chip.Chip

class GroupMemberFirstFragment : Fragment() {
    lateinit var groupMemberFirstFragmentBinding: FragmentMemberFirstBinding
    val act : MainActivity by lazy { activity as MainActivity }
    val groupViewModel : GroupViweModel by lazy { ViewModelProvider(act).get(GroupViweModel::class.java) }
    val memberViewModel : MemberViewModel by lazy {ViewModelProvider(act).get(MemberViewModel::class.java)}
    val parentFragment : MyHomeEditFragment by lazy {getParentFragment() as MyHomeEditFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        groupMemberFirstFragmentBinding = FragmentMemberFirstBinding.inflate(inflater)
        val memberId = groupViewModel.nowGroupMemberId
        val result = memberViewModel.getMemberTotalInfo(memberId)
        result.observe(viewLifecycleOwner){
            groupMemberFirstFragmentBinding.profileName.text = it.name
            groupMemberFirstFragmentBinding.profileJob.text = it.job
            groupMemberFirstFragmentBinding.profileExplanation.text = it.content
            groupMemberFirstFragmentBinding.profilePhone.textView.text = it.phone
            groupMemberFirstFragmentBinding.profileEmail.textView.text = it.email
            val tagGroup = groupMemberFirstFragmentBinding.tagGroup
            tagGroup.removeAllViews()
            val tags = it.tag
            for (tag in tags) {
                tagGroup.addView(Chip(requireContext()).apply {
                    text = tag
//                    isCloseIconVisible = true// x 버튼 보이게하기
//                    // 클릭시 삭제 리스너
//                    setOnCloseIconClickListener{
//                        tagGroup.removeView(this)
//                    }
                })
            }
            groupMemberFirstFragmentBinding.memberFirstLayout.visibility = View.VISIBLE
        }
        return groupMemberFirstFragmentBinding.root
    }
}