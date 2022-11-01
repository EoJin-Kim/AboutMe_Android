package com.ej.aboutme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ej.aboutme.R
import com.ej.aboutme.dto.response.GroupSummaryDto
import com.google.android.material.card.MaterialCardView

class GroupAdapter(
    private val onClick: (GroupSummaryDto) -> Unit
): ListAdapter<GroupSummaryDto, GroupAdapter.GroupPickViewHolder>(GroupPickDiffCallback){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroupPickViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_summary_item,parent,false)
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
        private val groupNameTextView : TextView = itemView.findViewById(R.id.group_member_name)
        private val groupSummaryTextView : TextView = itemView.findViewById(R.id.group_summary)
        private val groupCountTextView : TextView = itemView.findViewById(R.id.group_count)

        private lateinit var groupSummaryDto: GroupSummaryDto

        fun bind(groupSummaryDto: GroupSummaryDto) {
            this.groupSummaryDto = groupSummaryDto
            groupNameTextView.text = groupSummaryDto.teamName
            groupSummaryTextView.text = groupSummaryDto.summary
            groupCountTextView.text = "${groupSummaryDto.count}명"

            materialCardView.setOnClickListener {
                onClick(groupSummaryDto)
            }
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