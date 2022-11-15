package com.ej.aboutme.fragment.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.ej.aboutme.MainActivity
import com.ej.aboutme.databinding.FragmentGroupCreateDialogBinding
import com.ej.aboutme.dto.request.CreateGroupDto
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.viewmodel.GroupViweModel
import com.ej.aboutme.viewmodel.MemberViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupCreateFragmentDialog() : DialogFragment() {

    lateinit var binding: FragmentGroupCreateDialogBinding
    val act : MainActivity by lazy { activity as MainActivity }
    private val groupViewModel: GroupViweModel by activityViewModels()
    private val memberViewModel: MemberViewModel by activityViewModels()
    val queryPreferences  = QueryPreferences()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGroupCreateDialogBinding.inflate(inflater)
        // dialog 모서리 둥글게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val memberId = queryPreferences.getUserId(requireContext())
        val groupNameText = binding.groupCreateName
        val groupSummaryText = binding.groupCreateSummary

        binding.createGroupBtn.setOnClickListener {
            val createGroupName = groupNameText.editText!!.text.toString()
            val groupSummary = groupSummaryText.editText!!.text.toString()
            val createGroupDto = CreateGroupDto(memberId,createGroupName,groupSummary)
            groupViewModel.createGroup(createGroupDto)
            dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        // dialog 넓이 80% 설정
        val params = dialog?.window?.attributes
        params?.width = resources.displayMetrics.widthPixels * 8 /10
        params?.height = resources.displayMetrics.heightPixels * 8 /10
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }
}