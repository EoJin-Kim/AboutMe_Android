package com.ej.aboutme.fragment.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.ej.aboutme.databinding.FragmentMemberInfoDialogBinding
import com.ej.aboutme.dto.response.MemberInfoDto
import com.ej.aboutme.viewmodel.GroupViweModel
import com.ej.aboutme.viewmodel.MemberViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemberInfoFragmentDialog(private val memberInfoDto : MemberInfoDto) : DialogFragment() {
    private val groupViewModel: GroupViweModel by activityViewModels()
    private val memberViewModel: MemberViewModel by activityViewModels()
    lateinit var binding : FragmentMemberInfoDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemberInfoDialogBinding.inflate(inflater)
        // dialog 모서리 둥글게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drawUi()
    }

    private fun drawUi() {
        binding.dialogTitle.text = memberInfoDto.title
        binding.dialogContent.text = memberInfoDto.content
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