package com.demo.reposData.data.mappers

import com.demo.reposData.data.model.JackwhartonResponseItem
import com.demo.reposData.data.model.ReposDataEntity
import com.demo.reposData.domain.model.ReposDataUI


fun List<ReposDataEntity>.toReposDataUI() = map {
    ReposDataUI(
        id = it.id,
        title = it.title,
        description  = it.description)
}

fun List<JackwhartonResponseItem>.toReposDataEntity() = map {
    ReposDataEntity(
        id = it.id,
        title = it.name,
        description = it.description)
}