package com.ej.aboutme.fragment.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.ej.aboutme.MainActivity
import com.ej.aboutme.databinding.FragmentCreateGroupDialogBinding
import com.ej.aboutme.dto.request.CreateGroupDto
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.viewmodel.GroupViweModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateGroupFragmentDialog(
    private val onClick: (CreateGroupDto) -> Unit
) : DialogFragment() {

    lateinit var createGroupFragmentDialogBinding: FragmentCreateGroupDialogBinding
    val act : MainActivity by lazy { activity as MainActivity }
    val groupViewModel : GroupViweModel by lazy { ViewModelProvider(act).get(GroupViweModel::class.java) }
    val queryPreferences  = QueryPreferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        createGroupFragmentDialogBinding = FragmentCreateGroupDialogBinding.inflate(inflater)
        // dialog 모서리 둥글게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)


        val memberId = queryPreferences.getUserId(requireContext())

        val groupNameText = createGroupFragmentDialogBinding.groupCreateName
        val groupSummaryText = createGroupFragmentDialogBinding.groupCreateSummary

        createGroupFragmentDialogBinding.createGroupBtn.setOnClickListener {
            val createGroupName = groupNameText.editText!!.text.toString()
            val groupSummary = groupSummaryText.editText!!.text.toString()
            val createGroupDto = CreateGroupDto(memberId,createGroupName,groupSummary)
            onClick(createGroupDto)
            dismiss()
        }

        return createGroupFragmentDialogBinding.root


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