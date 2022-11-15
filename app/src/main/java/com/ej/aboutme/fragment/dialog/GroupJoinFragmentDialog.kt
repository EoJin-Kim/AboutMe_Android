package com.ej.aboutme.fragment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.databinding.FragmentGroupJoinDialogBinding
import com.ej.aboutme.dto.request.JoinGroupDto
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.viewmodel.GroupViweModel
import com.ej.aboutme.viewmodel.MemberViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupJoinFragmentDialog(
) : BottomSheetDialogFragment() {
    lateinit var binding: FragmentGroupJoinDialogBinding
    val act : MainActivity by lazy { activity as MainActivity }
    private val groupViewModel: GroupViweModel by activityViewModels()
    private val memberViewModel: MemberViewModel by activityViewModels()
    val queryPreferences = QueryPreferences()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // dialog 모서리 둥글게
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGroupJoinDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.groupJoinBtn.setOnClickListener {
            val memberId = queryPreferences.getUserId(act)
            val groupName = binding.groupJoinName.editText?.text.toString()
            val password= binding.groupJoinPassword.editText?.text.toString()
            val joinGroupDto = JoinGroupDto(memberId,groupName,password)
            groupViewModel.joinGroup(joinGroupDto)
            dismiss()
        }
    }
}