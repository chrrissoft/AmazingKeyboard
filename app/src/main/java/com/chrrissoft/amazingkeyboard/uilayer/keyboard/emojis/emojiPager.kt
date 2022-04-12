package com.chrrissoft.amazingkeyboard.uilayer.keyboard.emojis

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EmojiPager(
    emojiList: List<List<List<Int>>>,
    connection: IMEService
) {
    HorizontalPager(count = emojiList.size, Modifier.fillMaxSize()) { page ->
        EmojiPage(emojiList[page], connection)
    }
}