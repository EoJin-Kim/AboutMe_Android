package com.ej.aboutme.fragment.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.ej.aboutme.MainActivity
import com.ej.aboutme.databinding.FragmentMemberInfoEditDialogBinding
import com.ej.aboutme.dto.request.MemberInfoContentDto
import com.ej.aboutme.dto.response.MemberInfoDto
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.viewmodel.MemberViewModel


class MemberInfoEditFragmentDialog(private val memberInfoDto : MemberInfoDto) : DialogFragment() {

    lateinit var memberInfoEditFragmentDialog: FragmentMemberInfoEditDialogBinding
    val act : MainActivity by lazy { activity as MainActivity }
    val memberViewModel : MemberViewModel by lazy { ViewModelProvider(act).get(MemberViewModel::class.java) }
    val queryPreferences  = QueryPreferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        memberInfoEditFragmentDialog = FragmentMemberInfoEditDialogBinding.inflate(inflater)

        // dialog 모서리 둥글게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        val dialogTitleEdit = memberInfoEditFragmentDialog.dialogTitleEdit
        val dialogContentEdit = memberInfoEditFragmentDialog.dialogContentEdit
        val dialogEditBtn = memberInfoEditFragmentDialog.dialogEditBtn

        dialogTitleEdit.text = memberInfoDto.title
        dialogContentEdit.editText?.setText(memberInfoDto.content)

        dialogEditBtn.setOnClickListener {
            val memberInfoId = memberInfoDto.id
            val updateTextStr = dialogContentEdit.editText?.text.toString()
            val memberInfoContentDto = MemberInfoContentDto(updateTextStr)
            memberViewModel.updateMemberInfo(memberInfoId,memberInfoContentDto)
        }

        memberViewModel.memberCardInfoList.observe(viewLifecycleOwner){
            memberViewModel.setMemberInfo(it)
            dismiss()
        }





        // Inflate the layout for this fragment
        return memberInfoEditFragmentDialog.root
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