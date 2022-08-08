package com.ej.aboutme.fragment.group

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.databinding.FragmentGroupMemberBinding
import com.ej.aboutme.databinding.FragmentMyHomeBinding


class GroupMemberFragment : Fragment() {


    lateinit var groupMemberFragmentBinding: FragmentGroupMemberBinding
    val act : MainActivity by lazy { activity as MainActivity }
    val groupMemberFirstFragment  = GroupMemberFirstFragment()
    val groupMemberMenuFragment = GroupMemberMenuFragment()
    val fragList  = arrayOf(groupMemberFirstFragment,groupMemberMenuFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        groupMemberFragmentBinding = FragmentGroupMemberBinding.inflate(LayoutInflater.from(container!!.context),container,false)
        return groupMemberFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter1 = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fragList.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragList[position]
            }
        }

        groupMemberFragmentBinding.groupMemberViewpager2.adapter = adapter1
        groupMemberFragmentBinding.groupMemberViewpager2.orientation = ViewPager2.ORIENTATION_VERTICAL
        groupMemberFragmentBinding.groupMemberViewpager2.isSaveEnabled= false

    }
    override fun onResume() {
        super.onResume()
        act.binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_arrow_back_24)
        act.binding.floatingActionButton.setOnClickListener { btn ->
//            act.setFragment("my_home")
            // backstack 설정

        }
    }

}