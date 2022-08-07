package com.ej.aboutme.fragment.group

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.databinding.FragmentMyGroupBinding
import com.ej.aboutme.databinding.FragmentOpenGroupBinding
import com.ej.aboutme.fragment.navi.MyHomeFragment
import com.ej.aboutme.viewmodel.GroupViweModel

private const val ARG_GROUP_ID = "group_id"
class OpenGroupFragment : Fragment() {

    val act : MainActivity by lazy { activity as MainActivity }
    val groupViewModel : GroupViweModel by lazy { ViewModelProvider(act).get(GroupViweModel::class.java) }

    lateinit var openGroupFragment : FragmentOpenGroupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        openGroupFragment = FragmentOpenGroupBinding.inflate(inflater)

        val groupIdText = openGroupFragment.groupIdText
        val groupId = groupViewModel.nowGroupId
        groupIdText.text = "$groupId"
        val totalGroupInfo = groupViewModel.getTotalGroupInfo(groupId)
        totalGroupInfo.observe(viewLifecycleOwner){


            openGroupFragment.groupLayout.visibility = View.VISIBLE
        }
        return openGroupFragment.root
    }

    companion object {
        fun newInstance(): OpenGroupFragment {
            return OpenGroupFragment()
        }
    }
}