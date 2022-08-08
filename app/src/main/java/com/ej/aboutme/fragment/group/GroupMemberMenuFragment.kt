package com.ej.aboutme.fragment.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ej.aboutme.databinding.FragmentGroupMemberBinding
import com.ej.aboutme.databinding.FragmentMemberMenuBinding

class GroupMemberMenuFragment : Fragment() {

    lateinit var groupMemberMenuFragmentBinding: FragmentMemberMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        groupMemberMenuFragmentBinding = FragmentMemberMenuBinding.inflate(inflater)
        return groupMemberMenuFragmentBinding.root
    }
}