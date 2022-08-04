package com.ej.aboutme.fragment.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.ej.aboutme.MainActivity
import com.ej.aboutme.adapter.CardEditAdapter
import com.ej.aboutme.databinding.FragmentMemberMenuEditBinding
import com.ej.aboutme.dto.response.MemberInfo
import com.ej.aboutme.fragment.dialog.MemberInfoFragmentDialog
import com.ej.aboutme.fragment.navi.MyHomeEditFragment
import com.ej.aboutme.viewmodel.MyHomeViewModel


class MemberMenuEditFragment : Fragment() {
    val act : MainActivity by lazy { activity as MainActivity }
    lateinit var memberEditMenuFragmentBinding : FragmentMemberMenuEditBinding
    val parentFragment : MyHomeEditFragment by lazy {getParentFragment() as MyHomeEditFragment }
    val myHomeViewModel : MyHomeViewModel by lazy { parentFragment.myHomeViewModel}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        memberEditMenuFragmentBinding = FragmentMemberMenuEditBinding.inflate(inflater)

        val memberInfoList = myHomeViewModel.memberInfo.value!!.memberInfo
        val funCardVal : (MemberInfo) -> Unit = {memberInfo -> cardEditDialog(memberInfo)}
        val cardEditAdapter = CardEditAdapter(funCardVal)
        cardEditAdapter.submitList(memberInfoList)
        val cardRecycler = memberEditMenuFragmentBinding.cardEditRecycler
        cardRecycler.adapter = cardEditAdapter
        cardRecycler.layoutManager = GridLayoutManager(requireContext(),2)

        return memberEditMenuFragmentBinding.root
    }

    private fun cardEditDialog(memberInfo : MemberInfo){
        val dialog = MemberInfoFragmentDialog(memberInfo)
        dialog.show(
            act.supportFragmentManager,"상세정보!"
        )
    }
}