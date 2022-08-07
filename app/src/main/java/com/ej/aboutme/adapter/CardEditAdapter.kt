package com.ej.aboutme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ej.aboutme.R
import com.ej.aboutme.dto.response.MemberInfoDto
import com.google.android.material.card.MaterialCardView

class CardEditAdapter (
    private val onClick: (MemberInfoDto) -> Unit
    ) : ListAdapter<MemberInfoDto, CardEditAdapter.CardEditViewHolder>(CardPickDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardEditViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.edit_card_item, parent, false)
        val holder = CardEditViewHolder(view, onClick)
        return holder
    }

    override fun onBindViewHolder(holder: CardEditViewHolder, position: Int) {
        val memberTotalInfo = getItem(position)
        holder.bind(memberTotalInfo)
    }

    class CardEditViewHolder(
        itemView: View,
        private val onClick: (MemberInfoDto) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val materialCardView: MaterialCardView = itemView.findViewById(R.id.edit_card_view)
        private val titleTextView: TextView = itemView.findViewById(R.id.edit_card_title)

        private lateinit var memberInfoDto: MemberInfoDto

        init {
            materialCardView.setOnClickListener {
                onClick(memberInfoDto)
            }
        }

        fun bind(memberInfoDto: MemberInfoDto) {
            this.memberInfoDto = memberInfoDto
            titleTextView.text = memberInfoDto.title
//            imageView
        }

    }
}
object CardPickDiffCallback : DiffUtil.ItemCallback<MemberInfoDto>(){
    override fun areItemsTheSame(oldItem: MemberInfoDto, newItem: MemberInfoDto): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MemberInfoDto, newItem: MemberInfoDto): Boolean {
        return oldItem == newItem

    }
}