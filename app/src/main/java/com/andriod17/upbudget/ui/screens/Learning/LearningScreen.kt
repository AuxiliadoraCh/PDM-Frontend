//package com.andriod17.upbudget.ui.screens.Learning
//
//
//
//
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.andriod17.upbudget.data.repository.Learning.LearningRepositoryImpl
//import com.andriod17.upbudget.ui.components.CustomScaffold
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.andriod17.upbudget.data.model.Learning.LearningItem
//import com.andriod17.upbudget.ui.components.CustomScaffold
//import com.andriod17.upbudget.ui.components.LearningCard
//import com.andriod17.upbudget.viewmodel.Learning.LearningViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.andriod17.upbudget.viewmodel.Learning.LearningViewModelFactory
//
//@Composable
//fun LearningScreen() {
//
//    val viewModel: LearningViewModel = viewModel(
//        factory = LearningViewModelFactory(LearningRepositoryImpl())
//    )
//
//    val learningItems by viewModel.learningItems.collectAsState()
//    val isLoading by viewModel.isLoading.collectAsState()
//    val error by viewModel.error.collectAsState()
//
//
//    LaunchedEffect(Unit) {
//        viewModel.loadLearningItems()
//    }
//
//    CustomScaffold(
//        content = { innerPadding ->
//            Box(
//                modifier = Modifier
//                    .padding(innerPadding)
//                    .fillMaxSize()
//            ) {
//                when {
//                    isLoading -> {
//                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//                    }
//
//                    error != null -> {
//                        Text(
//                            text = error ?: "Error",
//                            color = MaterialTheme.colorScheme.error,
//                            modifier = Modifier.align(Alignment.Center)
//                        )
//                    }
//
//                    else -> {
//                        LazyColumn(
//                            contentPadding = PaddingValues(16.dp),
//                            verticalArrangement = Arrangement.spacedBy(16.dp)
//                        ) {
//                            items(learningItems) { item ->
//                                LearningCard(
//                                    title = item.title,
//                                    subtitle = item.subtitle,
//                                    description = item.description,
//                                    imageUrl = item.imageUrl
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    )
//
//}
