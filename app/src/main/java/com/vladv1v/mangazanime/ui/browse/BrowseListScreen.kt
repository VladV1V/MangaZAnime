package com.vladv1v.mangazanime.ui.browse

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vladv1v.mangazanime.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseListScreen() {
    Scaffold(topBar = {
        SmallTopAppBar({ Text(text = stringResource(id = R.string.browse_list)) })
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

        }
    }
}