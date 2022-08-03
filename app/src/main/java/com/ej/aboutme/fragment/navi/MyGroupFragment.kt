package com.ej.aboutme.fragment.navi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.viewmodel.MainViewModel
import com.ej.aboutme.databinding.FragmentMyGroupBinding


class MyGroupFragment : Fragment() {

    lateinit var myGroupFragmentBinding : FragmentMyGroupBinding
    lateinit var act : MainActivity
    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myGroupFragmentBinding = FragmentMyGroupBinding.inflate(LayoutInflater.from(container!!.context),container,false)
        act = activity as MainActivity
        viewModel = act.mainViewModel

        return myGroupFragmentBinding.root
    }
    override fun onResume() {
        super.onResume()
        act.binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_group_add_24)

        act.binding.floatingActionButton.setOnClickListener { btn ->
            Log.d("fab","myGroup")
        }
    }

    companion object {
        fun newInstance(): MyGroupFragment {
            return MyGroupFragment()
        }
    }
}