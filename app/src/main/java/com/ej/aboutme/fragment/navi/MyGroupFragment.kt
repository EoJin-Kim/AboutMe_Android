package com.ej.aboutme.fragment.navi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ej.aboutme.R
import com.ej.aboutme.databinding.FragmentMyGroupBinding


class MyGroupFragment : Fragment() {

    lateinit var myGroupFragmentBinding : FragmentMyGroupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myGroupFragmentBinding = FragmentMyGroupBinding.inflate(LayoutInflater.from(container!!.context),container,false)


        return myGroupFragmentBinding.root
    }
}