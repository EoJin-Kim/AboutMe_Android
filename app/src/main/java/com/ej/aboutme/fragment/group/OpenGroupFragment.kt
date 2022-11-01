package com.ej.aboutme.fragment.group

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.adapter.GroupMemberAdapter
import com.ej.aboutme.databinding.FragmentMyGroupBinding
import com.ej.aboutme.databinding.FragmentOpenGroupBinding
import com.ej.aboutme.dto.response.MemberInfoDto
import com.ej.aboutme.dto.response.MemberSummaryDto
import com.ej.aboutme.util.ServerInfo
import com.ej.aboutme.viewmodel.GroupViweModel

private const val ARG_GROUP_ID = "group_id"
class OpenGroupFragment : Fragment() {

    val act : MainActivity by lazy { activity as MainActivity }
    private val groupViewModel: GroupViweModel by activityViewModels()

    lateinit var openGroupFragment : FragmentOpenGroupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        openGroupFragment = FragmentOpenGroupBinding.inflate(inflater)

//        val groupIdText = openGroupFragment.groupIdText
        val groupId = groupViewModel.nowGroupId
//        groupIdText.text = "$groupId"

        val groupRecycler = openGroupFragment.groupMemberRecycler
        groupRecycler.layoutManager = LinearLayoutManager(act,LinearLayoutManager.HORIZONTAL,false)
        groupViewModel.groupTotal.observe(viewLifecycleOwner){
            openGroupFragment.openGroupName.text = it.groupName
            openGroupFragment.openGroupSummary.text = it.groupSummary
            openGroupFragment.openMemberCount.text = "총 ${it.count}명"

            val funGroupMemberVal : (MemberSummaryDto) -> Unit = { memberSummaryDto -> groupMemberOpenFragemnt(memberSummaryDto)}
            val funGroupMemberImage : (String,ImageView) -> Unit = { imageFullPath ,imageView -> GeoupMemberImageSet(imageFullPath,imageView)}
            val groupMemberAdapter =GroupMemberAdapter(funGroupMemberVal,funGroupMemberImage)
            groupRecycler.adapter = groupMemberAdapter
            groupMemberAdapter.submitList(it.memberSummary)
            openGroupFragment.groupLayout.visibility = View.VISIBLE
        }
        groupViewModel.getTotalGroupInfo(groupId)
        return openGroupFragment.root
    }

    private fun groupMemberOpenFragemnt(memberSummaryDto: MemberSummaryDto){
        groupViewModel.nowGroupMemberId = memberSummaryDto.id
        act.setFragment("open_member")
    }
    private fun GeoupMemberImageSet(imageFullPath : String, image : ImageView){
        Glide.with(act).load(ServerInfo.SERVER_IMAGE +imageFullPath).error(R.drawable.empty_img).into(image);
    }
    override fun onResume() {
        super.onResume()
        act.binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_arrow_back_24)
        act.binding.floatingActionButton.setOnClickListener { btn ->
//            val a = act.supportFragmentManager.backStackEntryCount
//            val b = act.supportFragmentManager.fragments.size
//            Log.d("Moon","B=$a")
//            Log.d("Moon","C=$b")



            act.supportFragmentManager.popBackStack()
//            act.supportFragmentManager.beginTransaction().remove(this).commit()
            // backstack 설정

        }
    }

    companion object {
        fun newInstance(): OpenGroupFragment {
            return OpenGroupFragment()
        }
    }
}