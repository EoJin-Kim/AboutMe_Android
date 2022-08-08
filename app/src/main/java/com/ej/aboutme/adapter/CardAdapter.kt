package com.ej.aboutme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ej.aboutme.dto.response.MemberInfoDto
import com.google.android.material.card.MaterialCardView

class CardAdapter(
    private val onClick: (MemberInfoDto) -> Unit
) : ListAdapter<MemberInfoDto,CardAdapter.CardPickViewHolder>(CardPickDiffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardPickViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item,parent,false)
        val holder = CardPickViewHolder(view,onClick)
        return holder
    }

    override fun onBindViewHolder(holder: CardPickViewHolder, position: Int) {
        val memberTotalInfo = getItem(position)
        holder.bind(memberTotalInfo)
    }

    class CardPickViewHolder(
        itemView : View,
        private val onClick: (MemberInfoDto) -> Unit
    ): RecyclerView.ViewHolder(itemView){
        private val materialCardView : MaterialCardView = itemView.findViewById(R.id.group_member_card_view)
        private val titleTextView : TextView= itemView.findViewById(R.id.group_member_name)
        private val imageView : ImageView = itemView.findViewById(R.id.card_image)

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