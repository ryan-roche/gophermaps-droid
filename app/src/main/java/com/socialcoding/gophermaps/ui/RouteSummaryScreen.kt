package com.socialcoding.gophermaps.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.socialcoding.gophermaps.R
import com.socialcoding.gophermaps.data.DataSource
import com.socialcoding.gophermaps.data.MapsUiState
import com.socialcoding.gophermaps.ui.theme.GopherMapsTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun RouteSummaryScreen(
    mapsUiState: MapsUiState,
    modifier: Modifier = Modifier,
    onNextButtonClicked: () -> Unit = {}
) {
    val startBuilding = mapsUiState.start_building
    val endBuilding = mapsUiState.end_building
    val startToEndString = stringResource(
        R.string.route_details,
        startBuilding,
        endBuilding
    )
//    val buildings = mapsUiState.buildings
    val buildings = DataSource.examplePreview
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        ){
            Box(modifier=Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    modifier = modifier,
                    text = startToEndString
                )
            }
            buildings.forEach{ item ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(8.dp).fillMaxWidth().background(Color.Cyan)
                ) {
                    Text(item)
                }
//                Image(
//                    painter = painterResource(R.drawable.building),
//                    contentDescription = item,
//                    contentScale = ContentScale.Crop
//                )
            }
        }
        Button(
            onClick = onNextButtonClicked
        ) {
            Text("  Go!  ")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RouteSummaryScreenPreview(){
    GopherMapsTheme {
        RouteSummaryScreen(
            mapsUiState = MapsUiState()
            //mapsUiState = MapsUiState(buildings = listOf("1","2","3"))
        )
    }
}