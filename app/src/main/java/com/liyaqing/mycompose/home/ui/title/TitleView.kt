package com.liyaqing.mycompose.home.ui.title

import androidx.annotation.IntegerRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @Author: liyaqing
 * @Date: Create in 15:57 2023/7/13
 * @Description:
 */
@Composable
fun Titleview(icon: Int, title: String?) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = icon), contentDescription = "",
            modifier = Modifier.padding(start = 10.dp)
        )
        Text(
            text = title!!,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 5.dp),
            color = Color.Black
        )
    }
}