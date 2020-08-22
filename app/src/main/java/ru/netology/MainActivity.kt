package ru.netology

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.ktor.client.HttpClient
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import io.ktor.http.ContentType
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import ru.netology.adapters.PostsRecyclerAdapter
import ru.netology.adapters.TopSpaceingItemDecoration
import ru.netology.dto.Post
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = SupervisorJob()
    private lateinit var data: ArrayList<Post>
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(coroutineContext).launch {
            withContext(Dispatchers.IO) {
                data = loadData()
            }
            recView.apply {
                adapter = PostsRecyclerAdapter(data)
                val topSpacingDecoration = TopSpaceingItemDecoration(10)
                addItemDecoration(topSpacingDecoration)
                layoutManager = LinearLayoutManager(this@MainActivity)
                progressbar.visibility = View.GONE
            }
        }
    }

    private suspend fun loadData(): ArrayList<Post> {
        val http = HttpClient {
            install(JsonFeature) {
                acceptContentTypes = listOf(
                    ContentType.Text.Plain,
                    ContentType.Application.Json
                )
                serializer = GsonSerializer()

            }
        }
        return http.get<ArrayList<Post>>("https://raw.githubusercontent.com/vadimarsh/ArshKotlin6.1/master/posts.json")
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}
