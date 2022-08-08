package com.ej.aboutme.fragment.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ej.aboutme.CardAdapter
import com.ej.aboutme.MainActivity
import com.ej.aboutme.databinding.FragmentGroupMemberBinding
import com.ej.aboutme.databinding.FragmentMemberMenuBinding
import com.ej.aboutme.dto.response.MemberInfoDto
import com.ej.aboutme.fragment.dialog.MemberInfoFragmentDialog
import com.ej.aboutme.viewmodel.GroupViweModel
import com.ej.aboutme.viewmodel.MemberViewModel

class GroupMemberMenuFragment : Fragment() {

    lateinit var groupMemberMenuFragmentBinding: FragmentMemberMenuBinding
    val act : MainActivity by lazy { activity as MainActivity }
    val groupViewModel : GroupViweModel by lazy { ViewModelProvider(act).get(GroupViweModel::class.java) }
    val memberViewModel : MemberViewModel by lazy { ViewModelProvider(act).get(MemberViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        groupMemberMenuFragmentBinding = FragmentMemberMenuBinding.inflate(inflater)
        val memberInfoList = memberViewModel.memberTotalInfo.value!!.memberInfo
        val funCardVal : (MemberInfoDto) -> Unit = { memberInfo -> cardDialog(memberInfo)}
        val cardAdapter = CardAdapter(funCardVal)
        cardAdapter.submitList(memberInfoList)
        val cardRecycler = groupMemberMenuFragmentBinding.cardRecycler
        cardRecycler.adapter = cardAdapter
        cardRecycler.layoutManager = GridLayoutManager(requireContext(),2)

        return groupMemberMenuFragmentBinding.root
    }

    private fun cardDialog(memberInfoDto : MemberInfoDto){
        val dialog = MemberInfoFragmentDialog(memberInfoDto)
        dialog.show(
            act.supportFragmentManager,"상세정보!"
        )
    }
}