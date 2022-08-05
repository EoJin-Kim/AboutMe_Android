package com.ej.aboutme.fragment.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.databinding.FragmentMemberInfoEditDialogBinding
import com.ej.aboutme.dto.response.MemberInfo
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.viewmodel.MyHomeViewModel


class MemberInfoEditFragmentDialog(private val memberInfo : MemberInfo) : DialogFragment() {

    lateinit var memberInfoEditFragmentDialog: FragmentMemberInfoEditDialogBinding
    val act : MainActivity by lazy { activity as MainActivity }
    val myHomeViewModel : MyHomeViewModel by lazy { ViewModelProvider(act).get(MyHomeViewModel::class.java) }
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

        dialogTitleEdit.text = memberInfo.title
        dialogContentEdit.editText?.setText(memberInfo.content)

        dialogEditBtn.setOnClickListener {
            val memberInfoId = memberInfo.id
            val result = myHomeViewModel.updateMemberInfo(memberInfoId)
            result.observe(viewLifecycleOwner){
                dismiss()
            }
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