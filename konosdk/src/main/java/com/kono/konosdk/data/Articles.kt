package com.kono.konosdk.data

import com.google.gson.annotations.SerializedName

data class Articles(
    @SerializedName("children")
    val children: List<Child>,
    @SerializedName("error")
    val error: String?,
    @SerializedName("folder")
    val folder: Folder,
    @SerializedName("orderChildrenIncreaseRight")
    val orderChildrenIncreaseRight: Boolean
) {
    data class Child(
        @SerializedName("base")
        val base: String,
        @SerializedName("customData")
        val customData: CustomData,
        @SerializedName("description")
        val description: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("keywords")
        val keywords: List<String>,
        @SerializedName("name")
        val name: String,
        @SerializedName("orderInParent")
        val orderInParent: Int,
        @SerializedName("parent")
        val parent: String,
        @SerializedName("pcid")
        val pcid: String,
        @SerializedName("res")
        val res: Res,
        @SerializedName("tags")
        val tags: List<String>,
        @SerializedName("type")
        val type: String
    ) {
        data class CustomData(
            @SerializedName("highlight")
            val highlight: Boolean
        )

        data class Res(
            @SerializedName("fitReading")
            val fitReading: FitReading,
            @SerializedName("html")
            val htmls: Htmls,
            @SerializedName("image")
            val image: Images
        ) {
            data class FitReading(
                @SerializedName("content")
                val content: String?,
                @SerializedName("file")
                val file: File,
                @SerializedName("secret")
                val secret: String?
            ) {
                data class File(
                    @SerializedName("hash")
                    val hash: String,
                    @SerializedName("id")
                    val id: String,
                    @SerializedName("uploadedHash")
                    val uploadedHash: String,
                    @SerializedName("uri")
                    val uri: String
                )
            }

            data class Htmls(
                @SerializedName("list")
                val list: List<Html>,
                @SerializedName("secret")
                val secret: String?,
                @SerializedName("startPageInParent")
                val startPageInParent: Int
            ) {
                data class Html(
                    @SerializedName("content")
                    val content: String?,
                    @SerializedName("hash")
                    val hash: String,
                    @SerializedName("id")
                    val id: String,
                    @SerializedName("uploadedHash")
                    val uploadedHash: String,
                    @SerializedName("uri")
                    val uri: String
                )
            }

            data class Images(
                @SerializedName("list")
                val list: List<Image>
            ) {
                data class Image(
                    @SerializedName("hash")
                    val hash: String,
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("id")
                    val id: String,
                    @SerializedName("thumbnails")
                    val thumbnails: List<Int>,
                    @SerializedName("uploadedHash")
                    val uploadedHash: String,
                    @SerializedName("uri")
                    val uri: String,
                    @SerializedName("width")
                    val width: Int
                )
            }
        }
    }

    data class Folder(
        @SerializedName("base")
        val base: String,
        @SerializedName("customData")
        val customData: String?,
        @SerializedName("description")
        val description: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("keywords")
        val keywords: List<String>,
        @SerializedName("name")
        val name: String,
        @SerializedName("orderChildrenIncreaseRight")
        val orderChildrenIncreaseRight: Boolean,
        @SerializedName("orderInParent")
        val orderInParent: Int,
        @SerializedName("parent")
        val parent: String,
        @SerializedName("pcid")
        val pcid: String,
        @SerializedName("res")
        val res: Res,
        @SerializedName("type")
        val type: String
    ) {
        data class Res(
            @SerializedName("image") //This has problem
            val images: Images
        ) {
            data class Images(
                @SerializedName("list")
                val list: List<Image>
            ) {
                data class Image(
                    @SerializedName("hash")
                    val hash: String,
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("id")
                    val id: String,
                    @SerializedName("thumbnails")
                    val thumbnails: List<Int>,
                    @SerializedName("uploadedHash")
                    val uploadedHash: String,
                    @SerializedName("uri")
                    val uri: String,
                    @SerializedName("width")
                    val width: Int
                )
            }
        }
    }
}