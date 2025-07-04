package com.andriod17.upbudget.ui.screens.Learning


import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
//import com.andriod17.upbudget.data.repository.Learning.LearningRepositoryImpl
import com.andriod17.upbudget.ui.components.CustomScaffold
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriod17.upbudget.data.model.Learning.LearningItem
import com.andriod17.upbudget.ui.components.CustomScaffold
import com.andriod17.upbudget.ui.components.LearningCard
import com.andriod17.upbudget.viewmodel.Learning.LearningViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.andriod17.upbudget.R
import com.andriod17.upbudget.ui.navigation.HomeNavigation
import com.andriod17.upbudget.viewmodel.Learning.LearningViewModelFactory

@Composable
fun LearningScreen(
    navController: NavHostController
) {
    val learningItems = listOf(
        LearningItem(
            title = "Ahorrar en compras",
            subtitle = "Consejos prácticos",
            description = "Aprende cómo reducir tus gastos diarios sin sacrificar calidad.",
            imageUrl = R.drawable.media,
        ),
        LearningItem(
            title = "Criptomonedas",
            subtitle = "Guía básica",
            description = "Conoce los fundamentos del mundo cripto y cómo empezar.",
            imageUrl = R.drawable.media
        )
    )

    CustomScaffold(
        title = "Educational Content",
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(learningItems) { item ->
                        LearningCard(
                            title = item.title,
                            subtitle = item.subtitle,
                            description = item.description,
                            imageResId = item.imageUrl
                        )
                    }
                }
            }
        },
        navController = navController,
        onBackPressed = {
            navController.navigate(HomeNavigation)
        }

    )
}

@Preview(showBackground = true)
@Composable
fun LearningScreenPreview() {
    //LearningScreen()
}


