package com.ej.aboutme.fragment.navi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.adapter.GroupAdapter
import com.ej.aboutme.viewmodel.MainViewModel
import com.ej.aboutme.databinding.FragmentMyGroupBinding
import com.ej.aboutme.dto.response.GroupSummaryDto
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.viewmodel.GroupViweModel


class MyGroupFragment : Fragment() {

    lateinit var myGroupFragmentBinding : FragmentMyGroupBinding

    val act : MainActivity by lazy { activity as MainActivity }
    val viewModel : MainViewModel by lazy { act.mainViewModel }
    val groupViewModel : GroupViweModel by lazy { ViewModelProvider(act).get(GroupViweModel::class.java) }

    val queryPreferences : QueryPreferences by lazy { QueryPreferences()}



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myGroupFragmentBinding = FragmentMyGroupBinding.inflate(LayoutInflater.from(container!!.context),container,false)

        val memberId = queryPreferences.getUserId(requireContext())
        val groupSummaryList = groupViewModel.getGroupSummaryList(memberId)


        val funGroupVal : (GroupSummaryDto) -> Unit = { groupSummaryDto -> createGroupFragemnt(groupSummaryDto)}
        val groupAdapter = GroupAdapter(funGroupVal)
        groupAdapter.submitList(groupSummaryList.value)
        val groupRecycler = myGroupFragmentBinding.groupRecycler
        groupRecycler.adapter = groupAdapter
        groupRecycler.layoutManager = LinearLayoutManager(requireContext())

        groupSummaryList.observe(viewLifecycleOwner){
            myGroupFragmentBinding.groupLayout.visibility = View.VISIBLE
            groupAdapter.submitList(it)
        }



        return myGroupFragmentBinding.root
    }
    override fun onResume() {
        super.onResume()
        act.binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_group_add_24)

//        act.binding.floatingActionButton.setOnClickListener { btn ->
//            Log.d("fab","myGroup")
//        }
    }
    fun createGroupFragemnt(groupSummaryDto: GroupSummaryDto){
        Log.d("group","test")
        return
    }

    companion object {
        fun newInstance(): MyGroupFragment {
            return MyGroupFragment()
        }
    }
}