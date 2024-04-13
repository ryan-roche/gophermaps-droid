package com.socialcoding.gophermaps.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.socialcoding.gophermaps.R
import com.socialcoding.gophermaps.data.DataSource
import com.socialcoding.gophermaps.ui.theme.GopherMapsTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun SelectBuildingScreen(
    modifier: Modifier = Modifier,
    buildings: List<String>,
    onNextButtonClicked: () -> Unit = {},
    onSelectionChanged: (String) -> Unit = {}
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(
        modifier=modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))){
            buildings.forEach{ item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                            onNextButtonClicked()
                        }
                    ) {
                        Text(item)
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi=true)
@Composable
fun SelectBuildingPreview(){
    GopherMapsTheme {
        SelectBuildingScreen(
            buildings = DataSource.startBuildings,
            //buildings = listOf("building 1", "building 2", "building 3","b4"),
            modifier = Modifier.fillMaxHeight()
        )
    }
}