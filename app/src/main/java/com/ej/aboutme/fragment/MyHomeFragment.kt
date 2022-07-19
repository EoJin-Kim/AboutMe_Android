package com.ej.aboutme.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.data.MainViewModel
import com.ej.aboutme.databinding.FragmentMyHomeBinding


class MyHomeFragment : Fragment() {

    lateinit var myHomeFragmentBinding: FragmentMyHomeBinding
    lateinit var viewModel : MainViewModel
    lateinit var act : MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myHomeFragmentBinding = FragmentMyHomeBinding.inflate(inflater)
        act = activity as MainActivity
        viewModel = act.mainViewModel



        viewModel.name.observe(viewLifecycleOwner){
            myHomeFragmentBinding.homeName.text = viewModel.name.value
        }
        viewModel.setName("어진1")
        viewModel.setName("어진2")





        return myHomeFragmentBinding.root
    }
}