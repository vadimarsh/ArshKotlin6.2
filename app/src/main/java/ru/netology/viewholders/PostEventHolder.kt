package ru.netology.viewholders

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_post_event.view.*
import ru.netology.R
import ru.netology.dto.Post
import ru.netology.dto.PostTypes

class PostEventHolder(itemView: View) : PostHolder(itemView) {

    override fun bind(post: Post) {
        refreshPost(post)
        initListiners(post)

        val requestOptions = RequestOptions().placeholder(R.drawable.avatar)
            .error(R.drawable.avatar)

        Glide.with(itemView.context).applyDefaultRequestOptions(requestOptions)
            .load(post.avatarurl).into(avatarIv)
        authorTv.text = post.author
        contentTv.text = post.content

        if (post.postType == PostTypes.POSTEVENT) {
            val location = "geo:" + post.coord!!.lat + ", " + post.coord.lon
            val uri: Uri = Uri.parse(location)
            itemView.geoTag.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                ContextCompat.startActivity(
                    itemView.context,
                    intent.apply { this.data = uri },
                    null
                )
            }
        }
    }


}