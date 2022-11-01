package com.ej.aboutme.fragment.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.databinding.FragmentMemberHomeBinding
import com.ej.aboutme.fragment.member.MemberHomeEditFragment
import com.ej.aboutme.util.ServerInfo
import com.ej.aboutme.viewmodel.GroupViweModel
import com.ej.aboutme.viewmodel.MemberViewModel
import com.google.android.material.chip.Chip

class GroupMemberFirstFragment : Fragment() {
    lateinit var binding: FragmentMemberHomeBinding
    val act : MainActivity by lazy { activity as MainActivity }
    private val groupViewModel: GroupViweModel by activityViewModels()
    private val memberViewModel: MemberViewModel by activityViewModels()
    val parentFragment : MemberHomeEditFragment by lazy {getParentFragment() as MemberHomeEditFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemberHomeBinding.inflate(inflater)
        val displayMetrics = act.applicationContext.resources.displayMetrics
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        val imageLayoutParams = binding.profileImage.layoutParams
        imageLayoutParams.height = height/3
        imageLayoutParams.width = height/3


        memberViewModel.memberTotalInfo.observe(viewLifecycleOwner){
            if(it.image!=""){
                val imageFullUrl = "${ServerInfo.SERVER_IMAGE}${it.image}"
                Glide.with(act).load(imageFullUrl).error(R.drawable.empty_img).into(binding.profileImage);
            }

            binding.profileName.text = it.name
            binding.profileJob.text = it.job
            binding.profileExplanation.text = it.content
            binding.profilePhone.text = it.phone
            binding.profileEmail.text = it.email
            val tagGroup = binding.tagGroup
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
        }

        val memberId = groupViewModel.nowGroupMemberId
        memberViewModel.getMemberTotalInfo(memberId)

        return binding.root
    }
}