package com.retrafit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.retrafit.adapter.UserAdapter
import com.retrafit.model.Users
import com.retrafit.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val apiInterface = ApiInterface.create().getMovies()
        apiInterface.enqueue(object : Callback<ArrayList<Users>> {
            override fun onResponse(
                call: Call<ArrayList<Users>>,
                response: Response<ArrayList<Users>>
            ) {

                if (response.body() != null) {
                    val usersAdapter = UserAdapter(response.body()!!)
                    recyclerView.adapter = usersAdapter
                }

            }

            override fun onFailure(call: Call<ArrayList<Users>>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Проверьте подключение интернета",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}