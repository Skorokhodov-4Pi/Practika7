package com.example.praktika1

import androidx.lifecycle.LiveData

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun shareByid(id: Long)
    fun removeById(id: Long)
    fun save(post: Post)

}