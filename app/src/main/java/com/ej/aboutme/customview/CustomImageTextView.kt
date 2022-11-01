package com.ej.aboutme.customview

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginLeft
import com.ej.aboutme.R

class CustomImageTextView : LinearLayout{
    val imageView : ImageView
    val textView : TextView
    init {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER
        imageView = ImageView(getContext())
        textView = TextView(getContext())
        addView(imageView)

        val param = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        param.leftMargin = 20
        addView(textView,param)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context,attrs){
        val attrList = context.obtainStyledAttributes(attrs, R.styleable.CustomImageTextView)
        for(i in 0 until attrList.indexCount){
            val attr = attrList.getIndex(i)
            when(attr){
                R.styleable.CustomImageTextView_text -> {
                    val content = attrList.getString(attr)
                    textView.text = content
                }
                R.styleable.CustomImageTextView_dot_image -> {
                    val rId = attrList.getResourceId(attr,R.drawable.ic_baseline_edit_24)
                    imageView.setImageResource(rId)
                    imageView.layoutParams.height = 50
                    imageView.layoutParams.width = 50
                }
            }
        }
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr:Int) : super(context,attrs,defStyleAttr){
    }
}