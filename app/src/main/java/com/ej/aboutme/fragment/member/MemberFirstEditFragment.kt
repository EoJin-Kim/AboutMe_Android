package com.ej.aboutme.fragment.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ej.aboutme.MainActivity
import com.ej.aboutme.databinding.FragmentMemberFirstEditBinding
import com.ej.aboutme.fragment.navi.MyHomeEditFragment
import com.ej.aboutme.viewmodel.MyHomeViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MemberFirstEditFragment : Fragment() {

    val act : MainActivity by lazy { activity as MainActivity }
    val parentFragment : MyHomeEditFragment by lazy {getParentFragment() as MyHomeEditFragment }
    val myHomeViewModel : MyHomeViewModel by lazy { parentFragment.myHomeViewModel}

    lateinit var memberFirstEditFragmentBinding : FragmentMemberFirstEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        memberFirstEditFragmentBinding = FragmentMemberFirstEditBinding.inflate(inflater)

        val memberInfo = myHomeViewModel.memberInfo.value
        memberFirstEditFragmentBinding.memberEditName.editText?.setText(memberInfo?.name)
        memberFirstEditFragmentBinding.memberEditJob.editText?.setText(memberInfo?.job)
        memberFirstEditFragmentBinding.memberEditPhone.editText?.setText(memberInfo?.phone)
        memberFirstEditFragmentBinding.memberEditContent.editText?.setText(memberInfo?.content)

        val tagTextView = memberFirstEditFragmentBinding.tagText
        val tagAddBtn = memberFirstEditFragmentBinding.tagAddBtn
        val tagGroup = memberFirstEditFragmentBinding.tagGroup

        tagAddBtn.setOnClickListener {
            val tagInputStr = tagTextView.editText?.text.toString()
            addTag(tagGroup,tagInputStr)
            tagTextView.editText?.setText("")
        }


        for (tagStr in memberInfo!!.tag) {
            addTag(tagGroup, tagStr)
        }



        return memberFirstEditFragmentBinding.root
    }

    private fun addTag(tagGroup: ChipGroup, tagStr: String) {
        tagGroup.addView(Chip(requireContext()).apply {
            text = tagStr
            isCloseIconVisible = true// x 버튼 보이게하기
            // 클릭시 삭제 리스너
            setOnCloseIconClickListener {
                tagGroup.removeView(this)
            }
        })
    }


}