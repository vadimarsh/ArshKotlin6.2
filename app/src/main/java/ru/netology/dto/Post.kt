package ru.netology.dto

import ru.netology.util.Location
import java.util.*

data class Post(
    val id: Int,
    val postType: PostTypes,
    val author: String,
    val avatarurl: String,
    val content: String,
    var created: Date,
    var likes: Int = 0,
    var comments: Int = 0,
    var shares: Int = 0,
    var likedByMe: Boolean = false,
    var commentedByMe: Boolean = false,
    var sharedByMe: Boolean = false,
    val address: String? = null,
    val coord: Location? = null,
    val videoUrl: String? = null,
    val repost: Post? = null,
    val promoImgUrl: String? = null,
    val promoUrl: String? = null
)