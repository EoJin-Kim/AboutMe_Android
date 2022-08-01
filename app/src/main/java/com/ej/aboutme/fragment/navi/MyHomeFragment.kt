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
import com.ej.aboutme.R
import com.ej.aboutme.viewmodel.MainViewModel
import com.ej.aboutme.databinding.FragmentMyHomeBinding
import com.ej.aboutme.fragment.member.MemberFirstFragment
import com.ej.aboutme.fragment.member.MemberMenuFragment

private const val ARG_EMAIL = "user_email"
class MyHomeFragment : Fragment() {

    lateinit var myHomeFragmentBinding: FragmentMyHomeBinding

    val act : MainActivity by lazy { activity as MainActivity }
    val viewModel : MainViewModel by lazy { act.mainViewModel }


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
//        myHomeFragmentBinding = FragmentMyHomeBinding.inflate(inflater)
        myHomeFragmentBinding = FragmentMyHomeBinding.inflate(LayoutInflater.from(container!!.context),container,false)

        act.binding.bottomAppBar.visibility = View.VISIBLE
        act.binding.floatingActionButton.visibility = View.VISIBLE



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
    override fun onResume() {
        super.onResume()
        act.binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_edit_24)
        act.binding.floatingActionButton.setOnClickListener { btn ->
            Log.d("fab","myHome")
            act.setFragment("my_home_edit")
        }
    }
    companion object {
        fun newInstance(email: String): MyHomeFragment {
            val args = Bundle().apply {
                putSerializable(ARG_EMAIL, email)
            }
            return MyHomeFragment().apply {
                arguments = args
            }
        }
    }
}