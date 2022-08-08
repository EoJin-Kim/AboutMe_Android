package com.ej.aboutme.fragment.navi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.viewmodel.MainViewModel
import com.ej.aboutme.databinding.FragmentMyHomeEditBinding
import com.ej.aboutme.fragment.member.MemberFirstEditFragment
import com.ej.aboutme.fragment.member.MemberMenuEditFragment
import com.ej.aboutme.viewmodel.MemberViewModel


class MyHomeEditFragment : Fragment() {

    lateinit var myHomeEditFragmentBinding: FragmentMyHomeEditBinding
    val act : MainActivity by lazy { activity as MainActivity }
    val memberViewModel : MemberViewModel by lazy { ViewModelProvider(act).get(MemberViewModel::class.java) }
    val viewModel : MainViewModel by lazy { act.mainViewModel }

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
        act.binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_save_24)
        act.binding.floatingActionButton.setOnClickListener { btn ->
            Log.d("fab","myHomeEdit")
            act.setFragment("my_home")

        }
    }

    companion object {
        fun newInstance(): MyHomeEditFragment {
            return MyHomeEditFragment()
        }
    }


}