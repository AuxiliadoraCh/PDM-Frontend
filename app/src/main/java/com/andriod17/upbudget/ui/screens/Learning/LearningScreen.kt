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
            title = "Ahorro",
            subtitle = "Consejos prácticos",
            description = "El ahorro es la parte del dinero que decides no gastar hoy para usarlo en el futuro. Es una herramienta fundamental para alcanzar metas financieras, como comprar algo importante, pagar estudios o viajar, pero también para protegerte en caso de imprevistos, como una emergencia médica o pérdida de empleo.",
            imageUrl = R.drawable.ahorro,
        ),
        LearningItem(
            title = "Impuestos",
            subtitle = "Guía básica",
            description = "Los impuestos son pagos obligatorios que las personas y empresas hacen al Estado para financiar servicios públicos como salud, educación, seguridad, infraestructura y más.\n" +
                    "No son voluntarios ni opcionales, y su monto depende de factores como tus ingresos, el tipo de bienes que compras o los servicios que usas.",
            imageUrl = R.drawable.media,
        ),
        LearningItem(
            title = "Inversión",
            subtitle = "Guía básica",
            description = "La inversión es el acto de destinar una parte de tu dinero a un proyecto, producto o activo con el objetivo de obtener ganancias en el futuro. A diferencia del ahorro, que busca guardar dinero de forma segura, la inversión busca multiplicarlo con el tiempo, aunque implica cierto nivel de riesgo.",
            imageUrl = R.drawable.ingresos,
        ),
        LearningItem(
            title = "Inflación",
            subtitle = "Guía básica",
            description = "La inflación es el aumento general y sostenido de los precios de los bienes y servicios en una economía. Esto significa que, con el paso del tiempo, el dinero pierde poder adquisitivo, ya que con la misma cantidad puedes comprar menos cosas.",
            imageUrl = R.drawable.inflacion,
        ),
    )

    CustomScaffold(
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


