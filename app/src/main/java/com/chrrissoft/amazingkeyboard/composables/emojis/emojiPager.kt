package com.chrrissoft.amazingkeyboard.composables.emojis

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EmojiPager(
    emojiList: List<List<List<Int>>>,
) {
    HorizontalPager(count = emojiList.size, Modifier.fillMaxSize()) { page ->
        EmojiPage(emojiList[page])
    }
}