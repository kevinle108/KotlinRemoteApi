package com.example.gettingstarted

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gettingstarted.api.SearchResult
import com.example.gettingstarted.api.createGithubApiService
import com.example.gettingstarted.models.Repo
import com.example.gettingstarted.repolist.ReposAdapter
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private val adapter = ReposAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list: RecyclerView = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter


        val service = createGithubApiService()
        service.searchRepositories("android").enqueue(object: Callback,
            retrofit2.Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                val repos = response.body()?.items.orEmpty()
                adapter.submitList(repos)
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                // handle failure
            }

        })

//        val sampleData = listOf(
//            Repo("Repo 1"),
//            Repo("Repo 2"),
//            Repo("Repo 3"),
//            Repo("Repo 4"),
//            Repo("Repo 5"),
//            Repo("Repo 6"),
//            Repo("Repo 7"),
//            Repo("Repo 8"),
//            Repo("Repo 9"),
//            Repo("Repo 10"),
//        )
//
//        adapter.submitList(sampleData)

    }
}