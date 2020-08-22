package ru.netology.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_post_repost.view.*
import ru.netology.R
import ru.netology.dto.Post
import ru.netology.util.verboseTime
import java.util.*

abstract class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val avatarIv = itemView.avatarIv
    val authorTv = itemView.authorTv
    val contentTv = itemView.contentTv
    val createdTv = itemView.createdTv

    val likeButton = itemView.likeButton
    val commentButton = itemView.commentButton
    val shareButton = itemView.shareButton
    val likeTextView = itemView.likeTv
    val commentTextView = itemView.commentTv
    val shareTextView = itemView.shareTv
    val createdTvRepost = itemView.createdTvRepost
    val authorTvRepost = itemView.authorTvRepost


    abstract fun bind(post: Post)


    fun refreshPost(post: Post) {
        val curentDate = Date(System.currentTimeMillis())
        val elapsed = (curentDate.time - post.created.time) / 1_000
        createdTv.text = verboseTime(elapsed)

        authorTv.text = post.author
        contentTv.text = post.content
        likeTextView.text = post.likes.toString()
        commentTextView.text = post.comments.toString()
        shareTextView.text = post.shares.toString()


        if (post.likes == 0) likeTextView.visibility = View.INVISIBLE else likeTextView.visibility =
            View.VISIBLE
        if (post.comments == 0) commentTextView.visibility =
            View.INVISIBLE else commentTextView.visibility =
            View.VISIBLE
        if (post.shares == 0) shareTextView.visibility =
            View.INVISIBLE else shareTextView.visibility =
            View.VISIBLE

        if (post.likedByMe) {
            likeButton.setImageResource(R.drawable.like_active)
            likeTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorRed))
        } else {
            likeButton.setImageResource(R.drawable.like_inactive)
            likeTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorBlack))
        }
        if (post.commentedByMe) {
            commentButton.setImageResource(R.drawable.comment_active)
            commentTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorRed))
        } else {
            commentButton.setImageResource(R.drawable.comment)
            commentTextView.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.colorBlack
                )
            )
        }
        if (post.sharedByMe) {
            shareButton.setImageResource(R.drawable.share_active)
            shareTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorRed))
        } else {
            shareButton.setImageResource(R.drawable.share)
            shareTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorBlack))
        }
    }

    fun initListiners(post: Post) {
        likeButton.setOnClickListener {
            if (post.likedByMe) {
                post.likes--
                post.likedByMe = false
            } else {
                post.likes++
                post.likedByMe = true
            }
            refreshPost(post)
        }
        commentButton.setOnClickListener {
            if (post.commentedByMe) {
                post.comments--
                post.commentedByMe = false
            } else {
                post.comments++
                post.commentedByMe = true
            }
            refreshPost(post)
        }
        shareButton.setOnClickListener {
            if (post.sharedByMe) {
                post.shares--
                post.sharedByMe = false
            } else {
                post.shares++
                post.sharedByMe = true
            }
            refreshPost(post)

        }
    }
}
