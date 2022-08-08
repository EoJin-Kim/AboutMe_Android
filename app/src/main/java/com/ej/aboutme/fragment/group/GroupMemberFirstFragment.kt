package com.ej.aboutme.fragment.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ej.aboutme.databinding.FragmentMemberFirstBinding
import com.ej.aboutme.databinding.FragmentMemberMenuBinding

class GroupMemberFirstFragment : Fragment() {
    lateinit var groupMemberFirstFragmentBinding: FragmentMemberFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        groupMemberFirstFragmentBinding = FragmentMemberFirstBinding.inflate(inflater)
        return groupMemberFirstFragmentBinding.root
    }
}