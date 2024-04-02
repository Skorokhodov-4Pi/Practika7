package com.example.praktika1

import adapter.OnInteractionListener
import adapter.PostsAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.praktika1.databinding.ActivityMainBinding
import utils.AndroidUtils


class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val viewModel: PostViewModel by viewModels()
            val adapter = PostsAdapter(object : OnInteractionListener {
                override fun onEdit(post: Post) {
                    viewModel.edit(post)
                }
                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }
                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }
                override fun onShare(post: Post) {
                    viewModel.shareByid(post.id)
                }
            })
            viewModel.edited.observe(this){ post->
                if(post.id == 0L){
                    return@observe
                }
                with(binding.content){
                    requestFocus()
                    setText(post.content)
                }
            }
            binding.list.adapter = adapter
            viewModel.data.observe(this) { posts ->
                adapter.submitList(posts)
            }
            binding.save.setOnClickListener {
                with(binding.content) {
                    if (text.isNullOrBlank()) {
                        Toast.makeText(
                            this@MainActivity,
                            "Content cant be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }

                    viewModel.changeContent(text.toString())
                    viewModel.save()

                    setText("")
                    clearFocus()
                    AndroidUtils.hideKeyboard(this)
                }
            }
        }


}

