package com.ej.aboutme.fragment.group

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.ej.aboutme.CardAdapter
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.databinding.FragmentGroupMemberBinding
import com.ej.aboutme.databinding.FragmentMemberHomeBinding
import com.ej.aboutme.dto.response.MemberInfoDto
import com.ej.aboutme.dto.response.MemberTotalInfoDto
import com.ej.aboutme.fragment.dialog.MemberInfoFragmentDialog
import com.ej.aboutme.fragment.member.MemberHomeEditFragment
import com.ej.aboutme.util.ServerInfo
import com.ej.aboutme.viewmodel.GroupViweModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupMemberFragment : Fragment() {
    lateinit var binding: FragmentMemberHomeBinding
    val act : MainActivity by lazy { activity as MainActivity }
    private val groupViewModel: GroupViweModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMemberHomeBinding.inflate(LayoutInflater.from(container!!.context),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawUi()
        groupViewModel.getGroupMemberTotalInfo()

    }
    override fun onResume() {
        super.onResume()
        act.binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_arrow_back_24)
        act.binding.floatingActionButton.setOnClickListener { btn ->
            act.supportFragmentManager.popBackStack()

        }
    }

    private fun drawUi() {
        setProfileImageSize()
        val funCardVal: (MemberInfoDto) -> Unit = { memberInfo -> cardDialog(memberInfo) }
        val cardAdapter = CardAdapter(funCardVal)
        groupViewModel.groupMemberTotalInfo.observe(viewLifecycleOwner) {
            setMemberInfo(it, cardAdapter)
        }
    }

    private fun setMemberInfo(
        it: MemberTotalInfoDto,
        cardAdapter: CardAdapter
    ) {
        if (it.image != "") {
            val imageFullUrl = "${ServerInfo.SERVER_IMAGE}${it.image}"
            Glide.with(act).load(imageFullUrl).error(R.drawable.empty_img)
                .into(binding.profileImage);
        }
        binding.profileName.text = it.name
        binding.profileJob.text = it.job
        binding.profileExplanation.text = it.content
        binding.profilePhone.text = it.phone
        binding.profileEmail.text = it.email
        val tagGroup = binding.tagGroup
        tagGroup.removeAllViews()
        val tags = it.tag
        for (tag in tags) {
            tagGroup.addView(Chip(requireContext()).apply {
                text = tag
            })
        }
        cardAdapter.submitList(it.memberInfo)
        binding.cardRecycler.apply {
            adapter = cardAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
            isNestedScrollingEnabled = false
        }
    }

    private fun setProfileImageSize() {
        val displayMetrics = act.applicationContext.resources.displayMetrics
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        val imageLayoutParams = binding.profileImage.layoutParams
        imageLayoutParams.height = height / 5
        imageLayoutParams.width = height / 5
    }



    private fun cardDialog(memberInfoDto : MemberInfoDto){
        val dialog = MemberInfoFragmentDialog(memberInfoDto)
        dialog.show(
            act.supportFragmentManager,"상세정보!"
        )
    }


    companion object {
        val TAG = "GroupMemberFragment"
        fun newInstance(): GroupMemberFragment {
            return GroupMemberFragment()
        }
    }

}