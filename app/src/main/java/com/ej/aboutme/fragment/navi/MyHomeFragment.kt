package com.ej.aboutme.fragment.navi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.adapter.FragPagerAdapter
import com.ej.aboutme.data.MainViewModel
import com.ej.aboutme.databinding.FragmentMyHomeBinding
import com.ej.aboutme.fragment.member.MemberFirstFragment
import com.ej.aboutme.fragment.member.MemberMenuFragment


class MyHomeFragment : Fragment() {

    lateinit var myHomeFragmentBinding: FragmentMyHomeBinding
    lateinit var viewModel : MainViewModel
    lateinit var act : MainActivity


    val memberFirstFragment  = MemberFirstFragment()
    val memberMenuFragment = MemberMenuFragment()
    val fragList = arrayOf(memberFirstFragment,memberMenuFragment)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myHomeFragmentBinding = FragmentMyHomeBinding.inflate(inflater)
        myHomeFragmentBinding = FragmentMyHomeBinding.inflate(LayoutInflater.from(container!!.context),container,false)
        act = activity as MainActivity
        viewModel = act.mainViewModel



        viewModel.name.observe(viewLifecycleOwner){
//            myHomeFragmentBinding.homeName.text = viewModel.name.value
        }
        viewModel.setName("어진1")
        viewModel.setName("어진2")

        val adapter1 = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fragList.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragList[position]
            }
        }

        myHomeFragmentBinding.viewpager2.adapter = adapter1
        myHomeFragmentBinding.viewpager2.orientation = ViewPager2.ORIENTATION_VERTICAL
        myHomeFragmentBinding.viewpager2.isSaveEnabled= false






        return myHomeFragmentBinding.root
    }
}