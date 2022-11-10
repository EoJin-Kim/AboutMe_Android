package com.ej.aboutme.fragment.member

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.ej.aboutme.CardAdapter
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.databinding.FragmentMemberHomeBinding
import com.ej.aboutme.viewmodel.MainViewModel
import com.ej.aboutme.dto.response.MemberInfoDto
import com.ej.aboutme.dto.response.MemberTotalInfoDto
import com.ej.aboutme.fragment.dialog.MemberInfoFragmentDialog
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.util.ServerInfo
import com.ej.aboutme.viewmodel.MemberViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_EMAIL = "user_email"

@AndroidEntryPoint
class MemberHomeFragment : Fragment() {

    lateinit var binding: FragmentMemberHomeBinding

    val act : MainActivity by lazy { activity as MainActivity }
    private val mainViewModel: MainViewModel by activityViewModels()
    private val memberViewModel: MemberViewModel by activityViewModels()
    val queryPreferences : QueryPreferences by lazy { QueryPreferences() }


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
        val memberId = queryPreferences.getUserId(requireContext())
        memberViewModel.getMemberTotalInfo(memberId)

    }

    private fun drawUi() {
        setProfileImageSize()
        val funCardVal: (MemberInfoDto) -> Unit = { memberInfo -> cardDialog(memberInfo) }
        val cardAdapter = CardAdapter(funCardVal)
        memberViewModel.memberTotalInfo.observe(viewLifecycleOwner) {
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

    override fun onResume() {
        super.onResume()
        act.binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_edit_24)
        act.binding.floatingActionButton.setOnClickListener { btn ->
            Log.d("fab","myHome")
            act.setFragment("my_home_edit")
        }
    }

    private fun cardDialog(memberInfoDto : MemberInfoDto){
        val dialog = MemberInfoFragmentDialog(memberInfoDto)
        dialog.show(
            act.supportFragmentManager,"상세정보!"
        )
    }

    companion object {
        val TAG = "MemberHomeFragment"
        fun newInstance(): MemberHomeFragment {
            return MemberHomeFragment()
        }
    }
}