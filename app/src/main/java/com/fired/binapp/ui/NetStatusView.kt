package com.fired.binapp.ui.theme

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.fired.binapp.R
import com.fired.binapp.ui.AppState
import kotlinx.coroutines.delay

/**
 * @author yaya (@yahyalmh)
 * @since 02th November 2022
 */

@Composable
fun NetStatusView(
    appState: AppState,
    isOffline: Boolean,
) {
    val hideOnlineViewDelay: Long = 2000
    LaunchedEffect(isOffline) {
        if (isOffline.not()) {
            delay(hideOnlineViewDelay)
            appState.isOnlineViewVisible = false
        }
    }

    Log.d("TAGG", "NetStatusView: $isOffline and isonlinevisibe: ${appState.isOnlineViewVisible}")
    OfflineView(isOffline = isOffline)
    OnlineView(appState.isOnlineViewVisible, isOnline = isOffline.not())
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
fun OnlineView(isOnlineViewVisible: Boolean, isOnline: Boolean) {
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
            visible = isOnlineViewVisible && isOnline,
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


@Preview(widthDp = 250, heightDp = 360, backgroundColor = 4654654)
@Composable
fun TxtViewPreview() {
    BinAppTheme {
        Surface {
//            NetStatusView(isOffline = true)
        }
    }
}