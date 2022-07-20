package com.ej.aboutme.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView

class CustomTextView : AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs:AttributeSet) : super(context,attrs)
    constructor(context: Context, attrs:AttributeSet, defStyleAttr:Int) : super(context,attrs,defStyleAttr)

}