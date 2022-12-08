package com.client.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.fired.core.base.ReferenceDevices
import com.fired.core.component.BaseColumn

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: MutableState<TextFieldValue>,
    searchViewModel: SearchViewModel = hiltViewModel(),
    onQueryChange: (TextFieldValue) -> Unit
) {
    val isQueryEmpty = query.value.text.isEmpty()
    Row(modifier = modifier.fillMaxWidth()) {
        TextField(
            modifier = modifier.fillMaxWidth(),
            value = query.value,
            onValueChange = {
                query.value = it
                onQueryChange(it)
            },
            placeholder = { if (isQueryEmpty) Text(text = stringResource(R.string.search)) },
            keyboardOptions = keyboardOptions(),
            leadingIcon = { LeadingIcon() },
            trailingIcon = { if (!isQueryEmpty) TrailingIcon(query) },
            colors = textFieldColors(),
            singleLine = true,
            textStyle = MaterialTheme.typography.body1
        )
    }
}

@Composable
private fun keyboardOptions() = KeyboardOptions(
    keyboardType = KeyboardType.Text,
    imeAction = ImeAction.Search
)

@Composable
private fun LeadingIcon() {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = stringResource(R.string.search),
        tint = Color.Gray
    )
}

@Composable
private fun TrailingIcon(query: MutableState<TextFieldValue>) {
    AnimatedVisibility(visible = true) {
        IconButton(onClick = { query.value = TextFieldValue() }) {
            Icon(Icons.Filled.Clear, contentDescription = null)
        }
    }
}

@Composable
private fun textFieldColors() = TextFieldDefaults.textFieldColors(
    textColor = Color.Gray,
    backgroundColor = colors.surface,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent
)

@Composable
@Preview
fun SearchBarPreview() {
    SearchBar(
        onQueryChange = {},
        query = remember { mutableStateOf(TextFieldValue()) }
    )
}