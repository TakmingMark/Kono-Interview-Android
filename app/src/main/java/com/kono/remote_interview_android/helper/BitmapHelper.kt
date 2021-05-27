package com.kono.remote_interview_android.helper

import android.graphics.Bitmap

class BitmapHelper {
    fun cutBitmap(bitMap: Bitmap, widthMultiplier: Double, heightMultiplier: Double): Bitmap {
        return Bitmap.createBitmap(
            bitMap,
            0,
            0,
            (bitMap.width * widthMultiplier).toInt(),
            (bitMap.height * heightMultiplier).toInt()
        )
    }
}