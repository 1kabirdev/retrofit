package com.retrafit

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
    }

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val notConnected = intent.getBooleanExtra(
                ConnectivityManager
                    .EXTRA_NO_CONNECTIVITY, false
            )
            if (notConnected) disconnected()
        }
    }

    private fun fetchUsers() {
        val apiInterface = ApiInterface.create().getListUser()
        apiInterface.enqueue(object : Callback<ArrayList<Users>> {
            override fun onResponse(
                call: Call<ArrayList<Users>>,
                response: Response<ArrayList<Users>>
            ) {
                if (response.body() == null) {
                    val usersAdapter = UserAdapter(response.body()!!)
                    recyclerView.adapter = usersAdapter
                }
            }

            override fun onFailure(call: Call<ArrayList<Users>>, t: Throwable) = Unit
        })
    }


    private fun disconnected() {
        recyclerView.visibility = View.INVISIBLE
        Toast.makeText(this, "Проверьте подключение к интернету", Toast.LENGTH_SHORT).show()
    }
}