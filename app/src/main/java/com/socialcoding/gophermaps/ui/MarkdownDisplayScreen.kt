package com.socialcoding.gophermaps.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.socialcoding.gophermaps.data.MapsUiState
import com.socialcoding.gophermaps.data.MarkdownScanner
//import io.noties.markwon.Markwon
//import io.noties.markwon.MarkwonPlugin
//import io.noties.markwon.MarkwonReducer
//import io.noties.markwon.core.MarkwonTheme

@Composable
fun MarkdownDisplayScreen(
    fname: String,
    modifier: Modifier = Modifier,
    uiState: MapsUiState,
//    textView: MarkwonView
    ){
        Box(
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ){

//            Markwon.setMarkdown()
//            markdown.setMarkdown(textView, MarkdownScanner.parseMD(""))
        }
}