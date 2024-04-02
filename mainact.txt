package com.example.praktika1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.praktika1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this){post ->
            with(binding){
                author.text = post.author
                published.text = post.published
                mainText.text = post.content
                textView5.text = post.likes.toString()
                textView6.text = post.share.toString()
                if (post.likedByMe) {
                    imageButton7?.setImageResource(R.drawable.like_placeholder_svgrepo_com)
                }
                imageButton8?.setOnClickListener {
                    post.share++
                    textView6.text = post.share.toString()
                    when {
                        post.share<1000 ->textView6.text =post.share.toString()
                        post.share in 1000..999999 ->textView6.text ="${post.share/1000}K"
                        else->textView6.text =String.format("%.1fM",post.share.toDouble()/1000000)
                    }

                }

                imageButton7?.setOnClickListener {
                    post.likedByMe = !post.likedByMe
                    imageButton7.setImageResource(
                        if (post.likedByMe) R.drawable.like_svgrepo_com__1_
                        else R.drawable.like_placeholder_svgrepo_com
                    )
                    if (post.likedByMe) post.likes++ else post.likes--
                    textView5.text = post.likes.toString()
                    when {
                        post.likes in 1000..999999 ->textView5.text ="${post.likes/1000}K"
                        post.likes<1000->textView5.text =post.likes.toString()
                        else->textView5.text =String.format("%.1fM",post.likes.toDouble()/1000000)
                    }
                }
            }
        }

    }

}

