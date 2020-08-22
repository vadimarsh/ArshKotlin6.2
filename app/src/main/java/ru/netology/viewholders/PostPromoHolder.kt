package ru.netology.viewholders

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_post_promo.view.*
import ru.netology.R
import ru.netology.dto.Post

class PostPromoHolder(itemView: View) : PostHolder(itemView) {

    val captionTv = itemView.captionTv
    override fun bind(post: Post) {
        refreshPost(post)
        initListiners(post)

        captionTv.text = post.content
        val requestOptionsPromo =
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context).applyDefaultRequestOptions(requestOptionsPromo)
            .load(post.promoImgUrl).into(itemView.contentIv)
        val uri: Uri = Uri.parse(post.promoUrl)


        itemView.contentIv.setOnClickListener {
            ContextCompat.startActivity(
                itemView.context,
                Intent(Intent.ACTION_VIEW).apply { this.data = uri }, null
            )
        }
    }


}