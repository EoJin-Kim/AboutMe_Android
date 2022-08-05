package com.ej.aboutme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ej.aboutme.R
import com.ej.aboutme.dto.response.MemberInfo
import com.google.android.material.card.MaterialCardView

class CardEditAdapter (
    private val onClick: (MemberInfo) -> Unit
    ) : ListAdapter<MemberInfo, CardEditAdapter.CardEditViewHolder>(CardPickDiffCallback) {
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
        private val onClick: (MemberInfo) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val materialCardView: MaterialCardView = itemView.findViewById(R.id.edit_card_view)
        private val titleTextView: TextView = itemView.findViewById(R.id.edit_card_title)

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