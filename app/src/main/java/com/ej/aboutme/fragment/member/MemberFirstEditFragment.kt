package com.ej.aboutme.fragment.member

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.databinding.FragmentMemberFirstEditBinding
import com.ej.aboutme.dto.request.MemberUpdateDto
import com.ej.aboutme.fragment.navi.MyHomeEditFragment
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.viewmodel.MyHomeViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MemberFirstEditFragment : Fragment() {

    val act : MainActivity by lazy { activity as MainActivity }
    val parentFragment : MyHomeEditFragment by lazy {getParentFragment() as MyHomeEditFragment }
    val myHomeViewModel : MyHomeViewModel by lazy { parentFragment.myHomeViewModel}
    val queryPreferences : QueryPreferences by lazy { QueryPreferences() }

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
        val memberInfo = myHomeViewModel.memberTotalInfo.value
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

    override fun onResume() {
        super.onResume()

        act.binding.floatingActionButton.setOnClickListener{
            Log.d("fab","mfef")
            val name = memberFirstEditFragmentBinding.memberEditName.editText?.text.toString()
            val job = memberFirstEditFragmentBinding.memberEditJob.editText?.text.toString()
            val phone = memberFirstEditFragmentBinding.memberEditPhone.editText?.text.toString()
            val content = memberFirstEditFragmentBinding.memberEditContent.editText?.text.toString()

            val tagStrList = mutableListOf<String>()
            val tagGroup = memberFirstEditFragmentBinding.tagGroup as ChipGroup
            val tagCount = tagGroup.childCount
            for (i in 0 until tagCount) {
                val chip = tagGroup.get(i) as Chip

                tagStrList.add(chip.text.toString())
            }
            val memberUpdateDto = MemberUpdateDto(name,job,phone, content,tagStrList)

            val memberId = queryPreferences.getUserId(act)
            val result = myHomeViewModel.updateMember(memberId,memberUpdateDto)
            result.observe(viewLifecycleOwner){
                myHomeViewModel.updateMemberInfo(memberId)
            }
            act.setFragment("my_home")
            return@setOnClickListener
        }
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