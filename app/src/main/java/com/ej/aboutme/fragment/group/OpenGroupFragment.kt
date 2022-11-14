package com.ej.aboutme.fragment.group

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.adapter.GroupMemberAdapter
import com.ej.aboutme.databinding.FragmentOpenGroupBinding
import com.ej.aboutme.dto.response.MemberSummaryDto
import com.ej.aboutme.fragment.member.MemberHomeFragment
import com.ej.aboutme.util.ServerInfo
import com.ej.aboutme.viewmodel.GroupViweModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_GROUP_ID = "group_id"
@AndroidEntryPoint
class OpenGroupFragment : Fragment() {

    val act : MainActivity by lazy { activity as MainActivity }
    private val groupViewModel: GroupViweModel by activityViewModels()

    lateinit var binding : FragmentOpenGroupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOpenGroupBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drawUi()
        val groupId = groupViewModel.nowGroupId
        groupViewModel.getTotalGroupInfo(groupId)
    }

    private fun drawUi(){
        val groupRecycler = binding.groupMemberRecycler
        groupRecycler.layoutManager = LinearLayoutManager(act, LinearLayoutManager.VERTICAL, false)
        groupViewModel.groupTotal.observe(viewLifecycleOwner) {
            binding.openGroupName.text = it.groupName
            binding.openGroupSummary.text = it.groupSummary
            binding.openMemberCount.text = "총 ${it.count}명"

            val funGroupMemberVal: (MemberSummaryDto) -> Unit =
                { memberSummaryDto -> groupMemberOpenFragemnt(memberSummaryDto) }

            val funGroupMemberImage: (String, ImageView) -> Unit =
                { imageFullPath, imageView -> GeoupMemberImageSet(imageFullPath, imageView) }

            val groupMemberAdapter = GroupMemberAdapter(funGroupMemberVal, funGroupMemberImage)

            groupRecycler.adapter = groupMemberAdapter
            groupMemberAdapter.submitList(it.memberSummary)
            binding.groupLayout.visibility = View.VISIBLE
        }
    }

    private fun groupMemberOpenFragemnt(memberSummaryDto: MemberSummaryDto){
        groupViewModel.nowGroupMemberId = memberSummaryDto.id
        act.setFragment(MemberHomeFragment.TAG)
    }
    private fun GeoupMemberImageSet(imageFullPath : String, image : ImageView){
        Glide.with(act).load(ServerInfo.SERVER_IMAGE +imageFullPath).error(R.drawable.empty_img).into(image);
    }
    override fun onResume() {
        super.onResume()
        act.binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_arrow_back_24)
        act.binding.floatingActionButton.setOnClickListener { btn ->
            act.supportFragmentManager.popBackStack()
        }
    }

    companion object {
        val TAG = "OpenGroupFragment"
        fun newInstance(): OpenGroupFragment {
            return OpenGroupFragment()
        }
    }
}