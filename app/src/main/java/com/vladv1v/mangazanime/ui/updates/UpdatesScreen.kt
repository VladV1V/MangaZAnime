package com.vladv1v.mangazanime.ui.updates

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vladv1v.mangazanime.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatesScreen() {
    Scaffold(topBar = {
        SmallTopAppBar({ Text(text = stringResource(id = R.string.updates)) })
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

        }
    }
}