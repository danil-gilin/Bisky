package com.example.bisky.domain.repository.archive.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("add_collection_anime")
data class AddAnime(
    @PrimaryKey()
    override val id: String,
    override val name: String,
    override val poster: String
): BaseAnimeCollection
