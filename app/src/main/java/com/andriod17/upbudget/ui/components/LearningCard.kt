package com.andriod17.upbudget.ui.components

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.andriod17.upbudget.ui.theme.LightPurple
import com.andriod17.upbudget.ui.theme.Pink80
import com.andriod17.upbudget.ui.theme.Purple40
import com.andriod17.upbudget.ui.theme.Purple80
import androidx.compose.runtime.*

@Composable
fun LearningCard(
    title: String,
    subtitle: String,
    description: String,
    imageUrl: String,
) {

    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightPurple
        ),
        border = BorderStroke(2.dp, Purple80),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(subtitle, style = MaterialTheme.typography.bodySmall)
            }

            if (isExpanded) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        description,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            OutlinedButton(
                modifier = Modifier
                    .align(Alignment.End),
                border = BorderStroke(1.2.dp, Purple40),
                shape = RoundedCornerShape(24.dp),
                onClick = { isExpanded = !isExpanded },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple80,
                    contentColor = Purple40

                )
            ) {
                Text(if (isExpanded) "View less" else "View more")
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
        imageUrl = "https://images.ctfassets.net/icup9dtik4s2/aFOgCx3IltseoCBMnLn1o/4ee7b0150eb6e555752c244ea8190fde/tax-saudi.jpg",
        description = "A description about on how to do tax return in El Salvador, oriented to the young public, to help them understand better the concepts of finance in the country",

        )
}