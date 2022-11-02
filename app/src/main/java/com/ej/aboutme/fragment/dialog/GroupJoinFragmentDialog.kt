package com.ej.aboutme.fragment.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.lifecycle.ViewModelProvider
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.databinding.FragmentGroupJoinDialogBinding
import com.ej.aboutme.dto.request.CreateGroupDto
import com.ej.aboutme.dto.request.JoinGroupDto
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.viewmodel.GroupViweModel
import com.ej.aboutme.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupJoinFragmentDialog(
    private val onClick: (JoinGroupDto) -> Unit
) : BottomSheetDialogFragment() {
    lateinit var groupJoinFragmentDialogBinding: FragmentGroupJoinDialogBinding
    val act : MainActivity by lazy { activity as MainActivity }
    val groupViewModel : GroupViweModel by lazy { ViewModelProvider(act).get(GroupViweModel::class.java) }
    val mainViewModel : MainViewModel by lazy { act.mainViewModel }
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
        groupJoinFragmentDialogBinding = FragmentGroupJoinDialogBinding.inflate(inflater)



        val groupJoinBtn = groupJoinFragmentDialogBinding.groupJoinBtn
        val groupJoinText = groupJoinFragmentDialogBinding.groupJoinName
        groupJoinBtn.setOnClickListener {
            val memberId = queryPreferences.getUserId(act)
            val groupName = groupJoinText.editText?.text.toString()
            val joinGroupDto = JoinGroupDto(memberId,groupName)
            onClick(joinGroupDto)
            dismiss()


        }
        return groupJoinFragmentDialogBinding.root
    }
}