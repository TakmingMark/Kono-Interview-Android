package com.kono.remote_interview_android.helper

import timber.log.Timber

class UrlHelper {
    companion object {
        private const val DOT_SPLIT = "."
        private const val KONO_STORAGE_BASE_URL =
            "https://storage.googleapis.com/kps_public_dev_thumbnails/"
        private const val LINK_IMAGE_SIZE = "-"
    }

    fun covertToKonoUrl(url: String, imageSize: Int): String {
        val urlStrs = url.split(DOT_SPLIT)
        val imageExtension = DOT_SPLIT + urlStrs[urlStrs.size - 1]
        val removeExtensionUrl = url.substring(0, url.length - (imageExtension.length))

        val imageUrl =
            KONO_STORAGE_BASE_URL + removeExtensionUrl + LINK_IMAGE_SIZE + imageSize + imageExtension
        Timber.d("imageUrl:$imageUrl")
        return imageUrl
    }
}