package ru.netology.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.netology.R
import ru.netology.dto.Post

class PostBasicHolder(itemView: View) : PostHolder(itemView) {

    override fun bind(post: Post) {
        refreshPost(post)
        initListiners(post)
        val requestOptions = RequestOptions().placeholder(R.drawable.avatar)
            .error(R.drawable.avatar)

        Glide.with(itemView.context).applyDefaultRequestOptions(requestOptions)
            .load(post.avatarurl).into(avatarIv)
        authorTv.text = post.author
        contentTv.text = post.content


    }

}