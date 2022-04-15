package com.retrafit.model

import com.google.gson.annotations.SerializedName

class Users {

    @SerializedName("id")
    private var id: String = ""

    @SerializedName("login")
    private var login: String = ""

    @SerializedName("avatar_url")
    private var avatar_url: String = ""

    fun getId(): String {
        return id
    }

    fun getLogin(): String {
        return login
    }

    fun getAvatarUrl(): String {
        return avatar_url
    }
}