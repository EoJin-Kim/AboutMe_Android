package com.ej.aboutme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ej.aboutme.CardAdapter
import com.ej.aboutme.R
import com.ej.aboutme.dto.response.GroupSummaryDto
import com.ej.aboutme.dto.response.MemberInfo
import com.google.android.material.card.MaterialCardView

class GroupAdapter(
    private val onClick: (GroupSummaryDto) -> Unit
): ListAdapter<GroupSummaryDto, GroupAdapter.GroupPickViewHolder>(GroupPickDiffCallback){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroupPickViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.group_summary_item,parent,false)
        val holder = GroupAdapter.GroupPickViewHolder(view, onClick)
        return holder
    }

    override fun onBindViewHolder(holder: GroupPickViewHolder, position: Int) {
        val groupSummaryDto = getItem(position)
        holder.bind(groupSummaryDto)
    }

    class GroupPickViewHolder(
        itemView : View,
        private val onClick: (GroupSummaryDto) -> Unit
    ): RecyclerView.ViewHolder(itemView){
        private val materialCardView : MaterialCardView = itemView.findViewById(R.id.group_card_view)
        private val groupNameTextView : TextView = itemView.findViewById(R.id.group_name)
        private val groupCountTextView : TextView = itemView.findViewById(R.id.group_count)

        private lateinit var groupSummaryDto: GroupSummaryDto

        init {
            materialCardView.setOnClickListener {
                onClick(groupSummaryDto)
            }
        }

        fun bind(groupSummaryDto: GroupSummaryDto) {
            this.groupSummaryDto = groupSummaryDto
            groupNameTextView.text = groupSummaryDto.teamName
            groupCountTextView.text = "${groupSummaryDto.count}"
//            imageView
        }

    }
}

object GroupPickDiffCallback : DiffUtil.ItemCallback<GroupSummaryDto>(){
    override fun areItemsTheSame(oldItem: GroupSummaryDto, newItem: GroupSummaryDto): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GroupSummaryDto, newItem: GroupSummaryDto): Boolean {
        return oldItem == newItem

    }
}