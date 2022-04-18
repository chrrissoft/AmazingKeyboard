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
import androidx.compose.material.icons.outlined.Forward
import androidx.compose.material.icons.rounded.FileUpload
import androidx.compose.material.icons.rounded.Forward
import androidx.compose.material.icons.rounded.Upload
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.common.DeleteKey
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.common.ShiftKey

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
    val isShiftedIcon = Icons.Rounded.Upload
    val notShiftIcon = Icons.Rounded.Forward
    val isShiftIcon = Icons.Outlined.Forward
    var isRotateIcon = true


    val shiftState by connection.shiftState.observeAsState()

    val interactionSource = remember { MutableInteractionSource() }

    var intervalDeleteText: Long = 650
    val deleteKeyInteractionSource = remember { MutableInteractionSource() }
    val isPressedDeleteKey by deleteKeyInteractionSource.collectIsPressedAsState()
    val deleteTextHandle = Handler(Looper.getMainLooper())

    val interval = object : Runnable {
        override fun run() {
            connection.deleteText()
            deleteTextHandle.postDelayed(this, intervalDeleteText)
            intervalDeleteText = 50
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        currentUnicodeList = if (
            shiftState == IMEService.ShiftState.SHIFT || shiftState == IMEService.ShiftState.SHIFTED) {
            unicodeListUppercase
        } else unicodeListLowercase

        val currentShiftIcon = when (shiftState) {
            IMEService.ShiftState.NOT_SHIFT -> {
                notShiftIcon
            }
            IMEService.ShiftState.SHIFTED -> {
                isRotateIcon = false
                isShiftedIcon
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
                    intervalDeleteText = 600
                }
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
                            isRotateIcon = isRotateIcon,
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
                                        when (shiftState) {
                                            IMEService.ShiftState.SHIFTED -> {
                                                connection.disableShift()
                                            }
                                            IMEService.ShiftState.SHIFT -> {
                                                connection.disableShift()
                                            }
                                            else -> { connection.enableShift() }
                                        }
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
                    ) {
                        if (shiftState != IMEService.ShiftState.SHIFTED) {
                            connection.disableShift()
                        }
                    }
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