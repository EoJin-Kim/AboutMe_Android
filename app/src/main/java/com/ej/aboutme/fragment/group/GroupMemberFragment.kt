package com.ej.aboutme.fragment.group

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.databinding.FragmentGroupMemberBinding
import com.ej.aboutme.databinding.FragmentMemberHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupMemberFragment : Fragment() {
    lateinit var binding: FragmentMemberHomeBinding
    val act : MainActivity by lazy { activity as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMemberHomeBinding.inflate(LayoutInflater.from(container!!.context),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onResume() {
        super.onResume()
        act.binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_arrow_back_24)
        act.binding.floatingActionButton.setOnClickListener { btn ->
            act.supportFragmentManager.popBackStack()

        }
    }

    companion object {
        val TAG = "GroupMemberFragment"
        fun newInstance(): GroupMemberFragment {
            return GroupMemberFragment()
        }
    }

}