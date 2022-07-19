package com.ej.aboutme.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ej.aboutme.fragment.member.MemberFirstFragment
import com.ej.aboutme.fragment.member.MemberMenuFragment

class FragPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    val memberFirstFragment  = MemberFirstFragment()
    val memberMenuFragment = MemberMenuFragment()


    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position){
            0 -> MemberFirstFragment()
            1 -> MemberMenuFragment()
            else -> null
        }
        return fragment!!
    }
}