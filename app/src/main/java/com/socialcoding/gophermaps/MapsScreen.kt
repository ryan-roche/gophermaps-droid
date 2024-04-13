package com.socialcoding.gophermaps

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.socialcoding.gophermaps.data.DataSource
import com.socialcoding.gophermaps.data.MapsUiState
import com.socialcoding.gophermaps.ui.MarkdownDisplayScreen
import com.socialcoding.gophermaps.ui.RouteSummaryScreen
import com.socialcoding.gophermaps.ui.SelectionViewModel
import com.socialcoding.gophermaps.ui.RouteSummaryScreen
import com.socialcoding.gophermaps.ui.SelectBuildingScreen

/**
 * enum values that represent the screens in the app
 */
enum class MapsScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    StartBuilding(title = R.string.choose_start),
    EndBuilding(title = R.string.choose_destination),
    Summary(title = R.string.route_summary),
    Instructions(title = R.string.instructions)
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsAppBar(
    currentScreen: MapsScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun MapsApp(
    viewModel: SelectionViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = MapsScreen.valueOf(
        backStackEntry?.destination?.route ?: MapsScreen.StartBuilding.name
    )

    Scaffold(
        topBar = {
            MapsAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = MapsScreen.StartBuilding.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = MapsScreen.StartBuilding.name) {
                val context = LocalContext.current
                SelectBuildingScreen(
                    //subtotal = uiState.price,
                    onNextButtonClicked = { navController.navigate(MapsScreen.EndBuilding.name) },
//                    onCancelButtonClicked = {
//                        cancelOrderAndNavigateToStart(viewModel, navController)
//                    },
                    buildings = DataSource.startBuildings,
                    onSelectionChanged = { viewModel.setFirst(it) },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = MapsScreen.EndBuilding.name) {
                SelectBuildingScreen(
                    //subtotal = uiState.price,
                    onNextButtonClicked = { navController.navigate(MapsScreen.Summary.name) },
//                    onCancelButtonClicked = {
//                        cancelOrderAndNavigateToStart(viewModel, navController)
//                    },
                    buildings = uiState.buildings,
                    onSelectionChanged = { viewModel.setDestination(it) },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = MapsScreen.Summary.name) {
                val context = LocalContext.current
                RouteSummaryScreen(
                    mapsUiState = uiState,
//                    onCancelButtonClicked = {
//                        cancelOrderAndNavigateToStart(viewModel, navController)
//                    },
//                    onSendButtonClicked = { subject: String, summary: String ->
//                        shareOrder(context, subject = subject, summary = summary)
//                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }
//            composable(route = MapsScreen.Instructions.name){
//                MarkdownDisplayScreen(
//                    uiState = uiState,
//                    modifier = Modifier.fillMaxHeight(),
//                    fname = "test.md"
//                )
//            }
        }
    }
}

/**
 * Resets the [MapsUiState] and pops up to [StartBuilding]
 */
private fun cancelAndNavigateToStart(
    viewModel: SelectionViewModel,
    navController: NavHostController
) {
    viewModel.resetSelection()
    navController.popBackStack(MapsScreen.StartBuilding.name, inclusive = false)
}

