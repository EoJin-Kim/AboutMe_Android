package com.ej.aboutme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ej.aboutme.CardAdapter
import com.ej.aboutme.R
import com.ej.aboutme.dto.response.GroupSummaryDto
import com.ej.aboutme.dto.response.MemberSummaryDto
import com.ej.aboutme.util.ServerInfo
import com.ej.aboutme.util.ServerInfo.Companion.SERVER_IMAGE
import com.google.android.material.card.MaterialCardView
import kotlin.coroutines.coroutineContext

class GroupMemberAdapter(
    private val onClick: (MemberSummaryDto) -> Unit,
    private val imageSet: (String,ImageView) -> Unit,
) :
    ListAdapter<MemberSummaryDto, GroupMemberAdapter.GroupMemberViewHolder>(GroupMemberDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroupMemberAdapter.GroupMemberViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.group_member_card_item,parent,false)
        val holder = GroupMemberAdapter.GroupMemberViewHolder(view, onClick,imageSet)
        return holder
    }

    override fun onBindViewHolder(holder: GroupMemberAdapter.GroupMemberViewHolder, position: Int) {
        val memberSummaryDto = getItem(position)
        holder.bind(memberSummaryDto)
    }

    class GroupMemberViewHolder(
        itemView : View,
        private val onClick: (MemberSummaryDto) -> Unit,
        private val imageSet: (String,ImageView) -> Unit,
    ) : RecyclerView.ViewHolder(itemView){
        private val materialCardView = itemView.findViewById<MaterialCardView>(R.id.group_member_card_view)
        private val groupMemberImage = itemView.findViewById<ImageView>(R.id.group_member_img)
        private val groupMemberName = itemView.findViewById<TextView>(R.id.group_member_name)
        private val groupMemberJob = itemView.findViewById<TextView>(R.id.group_member_job)
        private lateinit var memberSummaryDto: MemberSummaryDto

        fun bind(memberSummaryDto: MemberSummaryDto) {
            this.memberSummaryDto = memberSummaryDto
            groupMemberName.text = memberSummaryDto.name
            groupMemberJob.text = memberSummaryDto.job
            imageSet(memberSummaryDto.image,groupMemberImage)
//            if(memberSummaryDto.image!=""){
//            }

            materialCardView.setOnClickListener {
                onClick(memberSummaryDto)
            }
        }
    }
}


object GroupMemberDiffCallback : DiffUtil.ItemCallback<MemberSummaryDto>(){
    override fun areItemsTheSame(oldItem: MemberSummaryDto, newItem: MemberSummaryDto): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MemberSummaryDto, newItem: MemberSummaryDto): Boolean {
        return oldItem == newItem

    }
}