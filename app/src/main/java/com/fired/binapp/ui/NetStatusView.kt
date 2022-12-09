package com.fired.binapp.ui

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.fired.binapp.R
import kotlinx.coroutines.delay

/**
 * @author yaya (@yahyalmh)
 * @since 02th November 2022
 */

@Composable
fun NetStatusView(
    showOnlineView: Boolean,
    showOfflineView: Boolean,
) {
    OfflineView(isOffline = showOfflineView)
    OnlineView(isOnline = showOnlineView)
}

@Composable
fun OfflineView(isOffline: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = isOffline,
            enter = fadeIn(animationSpec = tween(500)) +
                    expandVertically(
                        animationSpec = tween(500)
                    ),
            exit = fadeOut(animationSpec = tween(500)) +
                    shrinkVertically(
                        animationSpec = tween(500)
                    )
        ) {
            val message = stringResource(R.string.offline)
            val color = Color.Red
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                text = message
            )
        }
    }
}

@Composable
fun OnlineView(isOnline: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        val message = stringResource(id = R.string.online)
        val color = Color.Green
        AnimatedVisibility(
            visible =  isOnline,
            enter = fadeIn(animationSpec = tween(500)) +
                    expandVertically(
                        animationSpec = tween(500)
                    ),
            exit = fadeOut(animationSpec = tween(500)) +
                    shrinkVertically(
                        animationSpec = tween(500)
                    )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                text = message
            )
        }
    }
}