package com.ej.aboutme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ej.aboutme.dto.response.MemberInfo
import com.ej.aboutme.dto.response.MemberTotalInfoDto
import com.google.android.material.card.MaterialCardView

class CardAdapter(
    private val onClick: (MemberInfo) -> Unit
) : ListAdapter<MemberInfo,CardAdapter.CardPickViewHolder>(CardPickDiffCallback){
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
        private val onClick: (MemberInfo) -> Unit
    ): RecyclerView.ViewHolder(itemView){
        private val materialCardView : MaterialCardView = itemView.findViewById(R.id.card_view)
        private val titleTextView : TextView= itemView.findViewById(R.id.card_text)
        private val imageView : ImageView = itemView.findViewById(R.id.card_image)

        private lateinit var memberInfo: MemberInfo

        init {
            materialCardView.setOnClickListener {
                onClick(memberInfo)
            }
        }

        fun bind(memberInfo: MemberInfo) {
            this.memberInfo = memberInfo
            titleTextView.text = memberInfo.title
//            imageView
        }

    }


}

object CardPickDiffCallback : DiffUtil.ItemCallback<MemberInfo>(){
    override fun areItemsTheSame(oldItem: MemberInfo, newItem: MemberInfo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MemberInfo, newItem: MemberInfo): Boolean {
        return oldItem == newItem

    }
}