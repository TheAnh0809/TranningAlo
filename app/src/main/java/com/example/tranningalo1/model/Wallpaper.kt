package com.example.tranningalo1.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Wallpaper : Serializable{
    val domain: String = "https://laptop.ahaa.com.vn/wall7storage/"
    @SerializedName("category")
    val category: String? = null

    @SerializedName("count")
    val count: Int? = null

    @SerializedName("countByCountry")
    val countByCountry: String? = null

    @SerializedName("country")
    val country: String? = null

    @SerializedName("defaultCountry")
    val defaultCountry: String? = null

    @SerializedName("downDate")
    val downDate: Long? = null

    @SerializedName("hashTag")
    val hashTag: String? = null

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("imgSize")
    val imgSize: String? = null

    @SerializedName("isApproved")
    val isApproved: String? = null

    @SerializedName("isVideo")
    val isVideo: Boolean? = null

    @SerializedName("lastPlayDate")
    val lastPlayDate: Long? = null

    @SerializedName("level")
    val level: Int? = null

    @SerializedName("like")
    val like: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("owner")
    val owner: String? = null

    @SerializedName("playCount")
    val playCount: Int? = null

    @SerializedName("screenRatio")
    val screenRatio: String? = null

    @SerializedName("searchName")
    val searchName: String? = null

    @SerializedName("unlike")
    val unlike: Int? = null

    @SerializedName("url")
    var url: String? = null

    private fun getFileName(url: String): String {
        val subString = url.split("/")
        if (subString.isNotEmpty()) return subString[subString.size - 1]
        return url
    }

    fun minThumbURLString(): String {
        return if (isVideo == true) {
            val fileName = getFileName(url ?: "")
            val lastPath = "thumbs/" + fileName.replace(".mp4", ".jpg")
            val path = url?.replace(fileName, lastPath)
            "${domain}$path"
        } else {
            "${domain}minthumbnails/$url"
        }
    }

    fun thumbUrlString(): String {
        return if (isVideo == true) {
            val fileName = getFileName(url ?: "")
            val lastPath = "thumbs/$fileName"
            val path = url?.replace(fileName, lastPath)
            "${domain}$path"
        } else {
            "${domain}thumbnails/$url"
        }
    }

    fun originUrlString(): String {
        return if (isVideo == true) {
            "${domain}$url"
        } else {
            "${domain}$url"
        }
    }
    fun sharetoString(): String{
        return "$name/$url"
    }
    fun getforshare(data: String) : Wallpaper{
        var list = data.split("/")
        var wall :Wallpaper = Wallpaper()
        wall.name = list.get(0)
        wall.url = list.get(1)
        return wall

    }
}