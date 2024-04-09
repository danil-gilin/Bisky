package com.example.bisky.ui.screen.searchscreen.quicksearch.model

import com.example.bisky.R
import com.example.bisky.common.model.BaseItem

data class AnimeBackInfoUI(
    override val itemId: String,
    val infoDuration: String,
    val infoStatus: String,
    val statusColor: Int,
    val infoType: String,
    val infoDate: String,
    val infoGenre: String,
    val infoProducer: String,
    val score: String,
    val scoreColor: Int,
    val isScoreVisible: Boolean,
    val age: String,
    val isAgeVisible: Boolean,
    val descriptionUI: AnimeDescriptionUI?,
    val screenshotList: List<String>
): BaseItem {
    companion object {
        val previewItemAnimeBackInfo = AnimeBackInfoUI(
            itemId = "itemId",
            infoDuration = "24 эп. по ~ 24 мин.",
            infoDate = "Осень 2012 г.",
            infoType = "Сериал",
            infoStatus = "вышел",
            statusColor = R.color.green,
            infoGenre = "боевик, боевик, боевик, боевик",
            infoProducer = "David Production",
            scoreColor = R.color.lime,
            score = "10",
            age = "16+",
            isAgeVisible = true,
            isScoreVisible = true,
            descriptionUI = AnimeDescriptionUI(
                itemId = "description",
                text = "Великобритания, XIX век. Джонатан Джостар, он же ДжоДжо, счастливо живёт в особняке со своим отцом. И вот у его семьи случается пополнение — отец семейства Джордж Джостар усыновляет странного паренька по имени Дио Брандо. Джонатан рад — у него появится новый друг. Однако Дио вовсе не настроен проявлять дружелюбие. Он задумал избавиться от единственного наследника Джостаров и заполучить всё их состояние себе. Мало того, что он избил Джостара-младшего в спарринге по боксу, настроил его друзей против Джонатана, так ещё и украл первый поцелуй у его девушки! Как будто этого мало, Дио сжигает в печи любимую собаку Джонатана — Денни. Но хуже всего то, что Дио тоже лыком не шит — на людях он ведёт себя как образцовый джентльмен. Тут терпению ДжоДжо приходит конец",
                isFullInfo = false
            ),
            screenshotList = listOf("1","2","3")
        )
    }
}

data class AnimeDescriptionUI(
    override val itemId: String,
    val text: String,
    val isFullInfo : Boolean
) : BaseItem
