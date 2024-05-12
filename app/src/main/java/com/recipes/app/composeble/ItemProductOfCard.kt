package com.recipes.app.composeble

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.recipes.R
import com.recipes.domain.entity.RecipeModel

@Composable
fun ItemProductOfCard(product: RecipeModel) {
    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, contentColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = "product thumbnail",
                    contentScale = ContentScale.Fit,
                    error = painterResource(id = R.drawable.recipe),
                    placeholder = painterResource(id = R.drawable.recipe),
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(150.dp)
                        .padding(all = 5.dp)
                )
                Column(
                    modifier = Modifier.padding(all = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = product.name,
                        modifier = Modifier.fillMaxWidth(),
                        color = colorResource(id = R.color.black),
                        fontSize = 23.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = product.cuisine,
                        modifier = Modifier.fillMaxWidth(),
                        color = colorResource(id = R.color.black),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}