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
import com.ej.aboutme.dto.response.MemberInfo
import com.ej.aboutme.fragment.dialog.MemberInfoFragmentDialog
import com.ej.aboutme.fragment.navi.MyHomeFragment
import com.ej.aboutme.viewmodel.MyHomeViewModel


class MemberMenuFragment : Fragment() {

    val act : MainActivity by lazy { activity as MainActivity }
    lateinit var memberMenuFragmentBinding : FragmentMemberMenuBinding
    val parentFragment : MyHomeFragment by lazy {getParentFragment() as MyHomeFragment }
    val myHomeViewModel : MyHomeViewModel by lazy { parentFragment.myHomeViewModel}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        memberMenuFragmentBinding = FragmentMemberMenuBinding.inflate(inflater)
        val memberInfoList = myHomeViewModel.memberInfo.value!!.response!!.memberInfo
        val funCardVal : (MemberInfo) -> Unit = {memberInfo -> cardDialog(memberInfo)}
        val cardAdapter = CardAdapter(funCardVal)
        cardAdapter.submitList(memberInfoList)
        val cardRecycler = memberMenuFragmentBinding.cardRecycler
        cardRecycler.adapter = cardAdapter
        cardRecycler.layoutManager = GridLayoutManager(requireContext(),2)



        return memberMenuFragmentBinding.root
    }


    private fun cardDialog(memberInfo : MemberInfo){
        val dialog = MemberInfoFragmentDialog(memberInfo)
        dialog.show(
            act.supportFragmentManager,"상세정보!"
        )
    }
}