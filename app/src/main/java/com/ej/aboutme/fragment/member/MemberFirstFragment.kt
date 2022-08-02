package com.ej.aboutme.fragment.member

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.api.AboutMeFetchr
import com.ej.aboutme.databinding.FragmentMemberFirstBinding
import com.ej.aboutme.databinding.FragmentMyGroupBinding
import com.ej.aboutme.dto.response.MemberInfoDto
import com.ej.aboutme.dto.response.ResponseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class MemberFirstFragment : Fragment() {

    lateinit var memeberFristFragmentBinding : FragmentMemberFirstBinding
    val act : MainActivity by lazy { activity as MainActivity }
    val aboutMeFetchr : AboutMeFetchr by lazy { AboutMeFetchr() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        memeberFristFragmentBinding = FragmentMemberFirstBinding.inflate(inflater)
        val displayMetrics = act.applicationContext.resources.displayMetrics
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        val imageLayoutParams = memeberFristFragmentBinding.profileImage.layoutParams
        imageLayoutParams.height = height/3
        imageLayoutParams.width = height/3

        val memberInfo : LiveData<ResponseDto<MemberInfoDto>> = aboutMeFetchr.getMemberInfo(1L)
        memberInfo.observe(viewLifecycleOwner){
            memeberFristFragmentBinding.memberFirstLayout.visibility = View.VISIBLE
        }


        return memeberFristFragmentBinding.root
    }

    companion object {
        fun newInstance(): MemberFirstFragment {
            return MemberFirstFragment()
        }
    }
}