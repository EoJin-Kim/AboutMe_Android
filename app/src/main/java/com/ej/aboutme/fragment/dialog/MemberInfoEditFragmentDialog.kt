package com.ej.aboutme.fragment.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.ej.aboutme.MainActivity
import com.ej.aboutme.databinding.FragmentMemberInfoEditDialogBinding
import com.ej.aboutme.dto.request.MemberInfoContentDto
import com.ej.aboutme.dto.response.MemberInfoDto
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.viewmodel.GroupViweModel
import com.ej.aboutme.viewmodel.MemberViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemberInfoEditFragmentDialog(private val memberInfoDto : MemberInfoDto) : DialogFragment() {

    lateinit var binding: FragmentMemberInfoEditDialogBinding
    val act : MainActivity by lazy { activity as MainActivity }
    private val groupViewModel: GroupViweModel by activityViewModels()
    private val memberViewModel: MemberViewModel by activityViewModels()
    val queryPreferences  = QueryPreferences()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMemberInfoEditDialogBinding.inflate(inflater)

        // dialog 모서리 둥글게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawUi()
        binding.dialogEditBtn.setOnClickListener {
            val memberInfoId = memberInfoDto.id
            val updateTextStr = binding.dialogContentEdit.editText?.text.toString()
            val memberInfoContentDto = MemberInfoContentDto(updateTextStr)
            memberViewModel.updateMemberInfo(memberInfoId,memberInfoContentDto)
        }

        memberViewModel.memberCardInfoList.observe(viewLifecycleOwner){
            memberViewModel.setMemberInfo(it)
            dismiss()
        }
    }

    private fun drawUi() {
        val dialogTitleEdit = binding.dialogTitleEdit
        val dialogContentEdit = binding.dialogContentEdit

        dialogTitleEdit.text = memberInfoDto.title
        dialogContentEdit.editText?.setText(memberInfoDto.content)
    }

    override fun onResume() {
        super.onResume()
        // dialog 넓이 80% 설정
        val params = dialog?.window?.attributes
        params?.width = resources.displayMetrics.widthPixels * 8 /10
        params?.height = resources.displayMetrics.heightPixels * 5 /10
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }
}