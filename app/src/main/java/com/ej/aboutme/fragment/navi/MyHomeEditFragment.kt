package com.ej.aboutme.fragment.navi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ej.aboutme.MainActivity
import com.ej.aboutme.data.MainViewModel
import com.ej.aboutme.databinding.FragmentMyHomeEditBinding
import com.ej.aboutme.fragment.member.MemberFirstEditFragment
import com.ej.aboutme.fragment.member.MemberMenuEditFragment


class MyHomeEditFragment : Fragment() {

    lateinit var myHomeEditFragmentBinding: FragmentMyHomeEditBinding
    lateinit var viewModel : MainViewModel
    lateinit var act : MainActivity

    val memberFirstEditFragment  = MemberFirstEditFragment()
    val memberMenuEditFragment = MemberMenuEditFragment()
    val fragList = arrayOf(memberFirstEditFragment,memberMenuEditFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        myHomeEditFragmentBinding = FragmentMyHomeEditBinding.inflate(inflater)
        myHomeEditFragmentBinding = FragmentMyHomeEditBinding.inflate(LayoutInflater.from(container!!.context),container,false)
        act = activity as MainActivity
        viewModel = act.mainViewModel

        val adapter1 = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fragList.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragList[position]
            }
        }

        myHomeEditFragmentBinding.viewpager2.adapter = adapter1
        myHomeEditFragmentBinding.viewpager2.orientation = ViewPager2.ORIENTATION_VERTICAL
        myHomeEditFragmentBinding.viewpager2.isSaveEnabled= false

        return myHomeEditFragmentBinding.root
    }

    override fun onResume() {
        super.onResume()
        act.binding.floatingActionButton.setOnClickListener { btn ->
            Log.d("fab","myHomeEdit")
        }
    }
}