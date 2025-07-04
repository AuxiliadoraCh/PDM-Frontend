package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriod17.upbudget.R
import com.andriod17.upbudget.ui.theme.LightPurple
import com.andriod17.upbudget.ui.theme.Purple40
import com.andriod17.upbudget.ui.theme.Purple80

@Composable
fun LearningCard(
    title: String,
    subtitle: String,
    description: String,
    imageResId: Int
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightPurple
        ),
        border = BorderStroke(1.dp, Purple80),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                OutlinedButton(
                    onClick = { isExpanded = !isExpanded },
                    border = BorderStroke(1.2.dp, Purple40),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Purple80,
                        contentColor = Purple40
                    ),
                    modifier = Modifier.height(36.dp)
                        .defaultMinSize(minWidth = 1.dp)
                ) {
                    Text(if (isExpanded) "Ver menos" else "Ver más")
                }
            }
            if (isExpanded) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Imagen educativa",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LearningCardPreview() {
    LearningCard(
        title = "How to do Tax-Return?",
        subtitle = "A step by step guide",
        imageResId = android.R.drawable.ic_menu_report_image, // usa aquí un drawable real
        description = "A description about how to do tax return in El Salvador, oriented to the young public, to help them understand better the concepts of finance in the country."
    )
}
