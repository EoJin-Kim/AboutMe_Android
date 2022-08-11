package com.ej.aboutme.fragment.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.api.AboutMeFetchr
import com.ej.aboutme.databinding.FragmentMemberFirstBinding
import com.ej.aboutme.dto.response.MemberTotalInfoDto
import com.ej.aboutme.fragment.navi.MyHomeFragment
import com.ej.aboutme.util.ServerInfo
import com.ej.aboutme.util.ServerInfo.Companion.SERVER_IMAGE
import com.ej.aboutme.viewmodel.MemberViewModel
import com.google.android.material.chip.Chip


class MemberFirstFragment : Fragment() {

    lateinit var memberFristFragmentBinding : FragmentMemberFirstBinding
    val act : MainActivity by lazy { activity as MainActivity }
    val parentFragment : MyHomeFragment by lazy {getParentFragment() as MyHomeFragment}
    val memberViewModel : MemberViewModel by lazy { parentFragment.memberViewModel}
    val aboutMeFetchr : AboutMeFetchr by lazy { AboutMeFetchr() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        memberFristFragmentBinding = FragmentMemberFirstBinding.inflate(inflater)
        val displayMetrics = act.applicationContext.resources.displayMetrics
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        val imageLayoutParams = memberFristFragmentBinding.profileImage.layoutParams
        imageLayoutParams.height = height/3
        imageLayoutParams.width = height/3


        val memberInfo : LiveData<MemberTotalInfoDto> = memberViewModel.memberTotalInfo

        memberInfo.observe(viewLifecycleOwner){
            if(it.image!=""){
                val imageFullUrl = "$SERVER_IMAGE${it.image}"
                Glide.with(act).load(imageFullUrl).error(R.drawable.empty_img).into(memberFristFragmentBinding.profileImage);
            }
            memberFristFragmentBinding.profileName.text = it.name
            memberFristFragmentBinding.profileJob.text = it.job
            memberFristFragmentBinding.profileExplanation.text = it.content
            memberFristFragmentBinding.profilePhone.textView.text = it.phone
            memberFristFragmentBinding.profileEmail.textView.text = it.email
            val tagGroup = memberFristFragmentBinding.tagGroup
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
            memberFristFragmentBinding.memberFirstLayout.visibility = View.VISIBLE
        }



        return memberFristFragmentBinding.root
    }

    companion object {
        fun newInstance(): MemberFirstFragment {
            return MemberFirstFragment()
        }
    }
}