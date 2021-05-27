package com.kono.remote_interview_android.widget

import android.content.Context
import android.util.AttributeSet


class CustomTextView : androidx.appcompat.widget.AppCompatTextView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    var onSizeChangedListener: OnSizeChangedListener? = null
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        onSizeChangedListener?.onSizeChanged()
    }


    interface OnSizeChangedListener {
        fun onSizeChanged()
    }
}