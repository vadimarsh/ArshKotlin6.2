package ru.netology.viewholders

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_post_video.view.*
import ru.netology.R
import ru.netology.dto.Post
import ru.netology.util.verboseTime
import java.util.*

class PostVideoHolder(itemView: View) : PostHolder(itemView) {
    override fun bind(post: Post) {
        refreshPost(post)
        initListiners(post)

        val requestOptions = RequestOptions().placeholder(R.drawable.avatar)
            .error(R.drawable.avatar)

        Glide.with(itemView.context).applyDefaultRequestOptions(requestOptions)
            .load(post.avatarurl).into(avatarIv)
        authorTv.text = post.author
        contentTv.text = post.content


        val curentDate = Date(System.currentTimeMillis())
        val elapsed = (curentDate.time - post.created.time) / 1_000
        createdTv.text = verboseTime(elapsed)


        val uri: Uri = Uri.parse(post.videoUrl)
        itemView.contentIv.setOnClickListener {
            ContextCompat.startActivity(
                itemView.context,
                Intent(Intent.ACTION_VIEW).apply { this.data = uri },
                null
            )
        }


    }
}