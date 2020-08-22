package ru.netology.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.R
import ru.netology.dto.Post
import ru.netology.dto.PostTypes.*
import ru.netology.viewholders.*

const val VIEW_POSTBASIC = 0
const val VIEW_POSTEVENT = 1
const val VIEW_POSTVIDEO = 2
const val VIEW_POSTREPOST = 3
const val VIEW_POSTPROMO = 4

class PostsRecyclerAdapter(dataSet: List<Post>) :
    RecyclerView.Adapter<PostHolder>() {

    private var items = dataSet
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {

        when (viewType) {
            VIEW_POSTBASIC ->
                return PostBasicHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_post_basic,
                        parent,
                        false
                    )
                )
            VIEW_POSTEVENT ->
                return PostEventHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_post_event,
                        parent,
                        false
                    )
                )
            VIEW_POSTVIDEO ->
                return PostVideoHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_post_video,
                        parent,
                        false
                    )
                )
            VIEW_POSTREPOST ->
                return PostRepostHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_post_repost,
                        parent,
                        false
                    )
                )
            VIEW_POSTPROMO ->
                return PostPromoHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_post_promo,
                        parent,
                        false
                    )
                )
            else -> return PostBasicHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_post_basic,
                    parent,
                    false
                )
            )
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].postType) {
            POSTBASIC -> VIEW_POSTBASIC
            POSTEVENT -> VIEW_POSTEVENT
            POSTVIDEO -> VIEW_POSTVIDEO
            POSTREPOST -> VIEW_POSTREPOST
            POSTPROMO -> VIEW_POSTPROMO
        }
    }
}