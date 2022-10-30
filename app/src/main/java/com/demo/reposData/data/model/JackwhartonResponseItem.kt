package com.demo.reposData.data.model


import com.google.gson.annotations.SerializedName

data class JackwhartonResponseItem(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String? = null
)