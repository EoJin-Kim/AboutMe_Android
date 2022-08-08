package com.ej.aboutme.fragment.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.ej.aboutme.CardAdapter
import com.ej.aboutme.MainActivity
import com.ej.aboutme.databinding.FragmentMemberMenuBinding
import com.ej.aboutme.dto.response.MemberInfoDto
import com.ej.aboutme.fragment.dialog.MemberInfoFragmentDialog
import com.ej.aboutme.fragment.navi.MyHomeFragment
import com.ej.aboutme.viewmodel.MemberViewModel


class MemberMenuFragment : Fragment() {

    val act : MainActivity by lazy { activity as MainActivity }
    lateinit var memberMenuFragmentBinding : FragmentMemberMenuBinding
    val parentFragment : MyHomeFragment by lazy {getParentFragment() as MyHomeFragment }
    val memberViewModel : MemberViewModel by lazy { parentFragment.memberViewModel}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        memberMenuFragmentBinding = FragmentMemberMenuBinding.inflate(inflater)
        val memberInfoList = memberViewModel.memberTotalInfo.value!!.memberInfo
        val funCardVal : (MemberInfoDto) -> Unit = { memberInfo -> cardDialog(memberInfo)}
        val cardAdapter = CardAdapter(funCardVal)
        cardAdapter.submitList(memberInfoList)
        val cardRecycler = memberMenuFragmentBinding.cardRecycler
        cardRecycler.adapter = cardAdapter
        cardRecycler.layoutManager = GridLayoutManager(requireContext(),2)



        return memberMenuFragmentBinding.root
    }


    private fun cardDialog(memberInfoDto : MemberInfoDto){
        val dialog = MemberInfoFragmentDialog(memberInfoDto)
        dialog.show(
            act.supportFragmentManager,"상세정보!"
        )
    }
}