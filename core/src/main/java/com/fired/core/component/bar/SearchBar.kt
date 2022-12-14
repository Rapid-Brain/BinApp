package com.fired.core.component.bar

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fired.core.R
import com.fired.core.component.icon.Icons

@Composable
fun SearchBar(onQueryChange: (query: String) -> Unit, onCancelClick: () -> Unit) {
    val requester = FocusRequester()
    var value by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .background(
                    MaterialTheme.colorScheme.secondary,
                    RoundedCornerShape(20)
                )
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Search,
                contentDescription = stringResource(id = R.string.search),
//                tint = MaterialTheme.colorScheme.onSurface
            )
            BasicTextField(modifier = Modifier
                .focusRequester(requester)
                .weight(1f)
                .padding(start = 2.dp)
                .height(24.dp)
                .background(color = Color.Transparent, shape = RoundedCornerShape(20)),
                singleLine = true,
                value = value,
                onValueChange = {
                    value = it
                    onQueryChange(it)
                },
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        AnimatedVisibility(
                            visible = value.isEmpty(),
                            enter = fadeIn(initialAlpha = 0.3f),
                            exit = fadeOut()
                        ) {
                            Text(
                                text = "Search Currencies",
                                color = MaterialTheme.colorScheme.onSecondary,
                            )
                        }
                    }
                    innerTextField()
                }
            )
            AnimatedVisibility(
                visible = value.isNotEmpty(),
                enter = fadeIn(initialAlpha = 0.3f),
                exit = fadeOut()
            ) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            value = ""
                            onQueryChange(value)
                        }
                        .size(20.dp),
                    imageVector = Icons.Close,
                    contentDescription = stringResource(id = R.string.search),
//                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        TextButton(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 4.dp, end = 4.dp),
            contentPadding = PaddingValues(4.dp),
            onClick = onCancelClick
        ) {
            Text(color = MaterialTheme.colorScheme.onSurface, fontSize = 16.sp, text = "Cancel")
        }
    }
    Spacer(
        modifier = Modifier
            .height(1.dp)
            .background(MaterialTheme.colorScheme.onPrimary)
    )
    SideEffect { requester.requestFocus() }
}

@Preview(showBackground = true, showSystemUi = false, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchPreviewDark() {
    SearchBar(onQueryChange = {}) {}
}

@Preview(showBackground = true, showSystemUi = false, uiMode = UI_MODE_NIGHT_MASK)
@Composable
fun SearchPreviewLight() {
    SearchBar(onQueryChange = {}) {}
}