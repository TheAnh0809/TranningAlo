package com.example.tranningalo1.model

data class Data(
    val nextImagePageNumber: Int,
    val nextVideoPageNumber: Int,
    val relateHashtags: List<RelateHashtag>,
    val wallpapers: List<Wallpaper>
)