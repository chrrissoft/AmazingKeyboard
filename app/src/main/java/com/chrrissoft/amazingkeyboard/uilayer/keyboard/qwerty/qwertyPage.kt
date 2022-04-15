package com.chrrissoft.amazingkeyboard.uilayer.keyboard.qwerty

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.composables.DeleteKey
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.composables.ShiftKey

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QwertyPage(
    unicodeListUppercase: List<List<String>>,
    unicodeListLowercase: List<List<String>>,
    connection: IMEService,
    modifier: Modifier = Modifier
) {
    val nextShitLower = "z"
    val nextShitUpper = "Z"
    val nextDeleteLower = "m"
    val nextDeleteUpper = "M"

    var currentRow = 0
    var currentKey = 0
    var currentUnicodeList: List<List<String>>

    // icons
    val isShiftPermanentIcon = Icons.Rounded.UploadFile
    val isNotShiftIcon = Icons.Rounded.Forward
    val isShiftIcon = Icons.Rounded.Adjust



    val isShift = connection.isShift.observeAsState()
    val isShifted = connection.isShifted.observeAsState()

    val interactionSource = remember { MutableInteractionSource() }

    var intervalDeleteText: Long = 600
    val deleteKeyInteractionSource = remember { MutableInteractionSource() }
    val isPressedDeleteKey by deleteKeyInteractionSource.collectIsPressedAsState()
    val deleteTextHandle = Handler(Looper.getMainLooper())

    val interval = object : Runnable {
        override fun run() {
            connection.deleteText()
            deleteTextHandle.postDelayed(this, intervalDeleteText)
            intervalDeleteText = 30
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        currentUnicodeList = if (isShift.value!! || isShifted.value!!) {
            unicodeListUppercase
        } else unicodeListLowercase

        val currentShiftIcon = when {
            !isShift.value!! -> {
                isNotShiftIcon
            }
            isShifted.value!! -> {
                isShiftPermanentIcon
            }
            else -> {
                isShiftIcon
            }
        }

        if (isPressedDeleteKey) {
            deleteTextHandle.post(interval)
            DisposableEffect(Unit) {
                onDispose {
                    deleteTextHandle.removeCallbacks(interval)
                    intervalDeleteText = 600 }
            }
        }

        currentUnicodeList.forEach { row ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                row.forEach { key ->
                    if (key == nextShitLower || key == nextShitUpper) {
                        ShiftKey(
                            icon = currentShiftIcon,
                            modifier = Modifier
                                .weight(1.5f)
                                .padding(start = 7.dp)
                                .combinedClickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onDoubleClick = {
                                        connection.enableShifted()
                                    },
                                    onClick = {
                                        connection.enableShift()
                                        connection.disableShifted()
                                    }
                                )
                        )
                    }
                    QwertyKey(
                        key = key,
                        connection = connection,
                        currentRow = currentRow,
                        currentKey = currentKey,
                        currentUnicodeList = currentUnicodeList,
                        modifier = Modifier
                            .weight(1f),
                    ) { connection.disableShift() }
                    if (key == nextDeleteLower || key == nextDeleteUpper)
                        DeleteKey(
                            Modifier
                                .weight(1.5f)
                                .padding(end = 7.dp)
                                .clickable(
                                    onClick = { },
                                    interactionSource = deleteKeyInteractionSource,
                                    indication = null
                                )
                        )
                    currentKey++
                }
            }
            currentRow++
            currentKey = 0
        }
    }
}