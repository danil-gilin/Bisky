package com.example.bisky.domain.repository.archive.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("complete_collection_anime")
data class CompleteAnime(
    @PrimaryKey()
    override val id: String,
    override val name: String,
    override val poster: String
): BaseAnimeCollection
