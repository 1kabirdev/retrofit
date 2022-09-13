package com.retrafit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.retrafit.R
import com.retrafit.model.Users
import com.squareup.picasso.Picasso

class UserAdapter(private var users: ArrayList<Users>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var viewHolder: ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(holder, users[position])
    }

    override fun getItemCount(): Int = users.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var image: ImageView = view.findViewById(R.id.image)
        private var login: TextView = view.findViewById(R.id.image)

        fun bindView(holder: ViewHolder, users: Users) {
            holder.login.text = users.login
            Picasso.get().load(users.avatar).into(holder.image)
        }
    }
}