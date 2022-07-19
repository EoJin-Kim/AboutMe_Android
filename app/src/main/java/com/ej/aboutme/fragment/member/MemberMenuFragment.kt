package com.ej.aboutme.fragment.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ej.aboutme.R
import com.ej.aboutme.databinding.FragmentMemberFirstBinding
import com.ej.aboutme.databinding.FragmentMemberMenuBinding


class MemberMenuFragment : Fragment() {

    lateinit var memeberMenuFragmentBinding : FragmentMemberMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        memeberMenuFragmentBinding = FragmentMemberMenuBinding.inflate(inflater)
        return memeberMenuFragmentBinding.root
    }


}