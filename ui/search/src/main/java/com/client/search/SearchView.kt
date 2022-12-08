package com.client.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fired.core.component.BaseColumn

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    BaseColumn {
        SearchBar(
            modifier = modifier, onQueryChange = {},
            query = remember { mutableStateOf(TextFieldValue()) }
        )
    }
}


@Composable
@Preview(showBackground = true)
fun SearchScreenPreview() {
    SearchScreen(navController = rememberNavController())
}