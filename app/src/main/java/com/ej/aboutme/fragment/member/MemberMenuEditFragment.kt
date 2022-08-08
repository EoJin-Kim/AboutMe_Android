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
import com.ej.aboutme.dto.response.MemberInfoDto
import com.ej.aboutme.fragment.dialog.MemberInfoEditFragmentDialog
import com.ej.aboutme.fragment.navi.MyHomeEditFragment
import com.ej.aboutme.viewmodel.MemberViewModel


class MemberMenuEditFragment : Fragment() {
    val act : MainActivity by lazy { activity as MainActivity }
    lateinit var memberEditMenuFragmentBinding : FragmentMemberMenuEditBinding
    val parentFragment : MyHomeEditFragment by lazy {getParentFragment() as MyHomeEditFragment }
    val memberViewModel : MemberViewModel by lazy { parentFragment.memberViewModel}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        memberEditMenuFragmentBinding = FragmentMemberMenuEditBinding.inflate(inflater)

        val memberInfoList = memberViewModel.memberTotalInfo.value!!.memberInfo
        val funCardVal : (MemberInfoDto) -> Unit = { memberInfo -> cardEditDialog(memberInfo)}
        val cardEditAdapter = CardEditAdapter(funCardVal)
        cardEditAdapter.submitList(memberInfoList)
        val cardRecycler = memberEditMenuFragmentBinding.cardEditRecycler
        cardRecycler.adapter = cardEditAdapter
        cardRecycler.layoutManager = GridLayoutManager(requireContext(),2)

        val memberInfo = memberViewModel.memberInfo
        memberInfo.observe(viewLifecycleOwner){
            cardEditAdapter.submitList(memberInfo.value!!)
        }

        return memberEditMenuFragmentBinding.root
    }

    private fun cardEditDialog(memberInfoDto : MemberInfoDto){
        val dialog = MemberInfoEditFragmentDialog(memberInfoDto)
        dialog.show(
            act.supportFragmentManager,"상세정보!"
        )
    }
}