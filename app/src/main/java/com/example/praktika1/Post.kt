package com.example.praktika1
data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int,
    var share: Long,
    var likedByMe: Boolean,
    var shareByMe: Boolean
)