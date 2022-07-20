package com.ej.aboutme.fragment.member

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.databinding.FragmentMemberFirstBinding
import com.ej.aboutme.databinding.FragmentMyGroupBinding


class MemberFirstFragment : Fragment() {

    lateinit var memeberFristFragmentBinding : FragmentMemberFirstBinding
    lateinit var act : MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        memeberFristFragmentBinding = FragmentMemberFirstBinding.inflate(inflater)
        act = activity as MainActivity
        val displayMetrics = act.applicationContext.resources.displayMetrics
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        val imageLayoutParams = memeberFristFragmentBinding.profileImage.layoutParams
        imageLayoutParams.height = height/3
        imageLayoutParams.width = height/3

        return memeberFristFragmentBinding.root
    }
}