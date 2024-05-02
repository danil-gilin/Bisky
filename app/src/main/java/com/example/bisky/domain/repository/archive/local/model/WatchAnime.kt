package com.example.bisky.domain.repository.archive.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("watch_collection_anime")
data class WatchAnime(
    @PrimaryKey()
    override val id: String,
    override val name: String,
    override val poster: String
): BaseAnimeCollection
