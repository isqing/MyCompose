package com.liyaqing.mycompose.home.ui.screen

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController

/**
 * @Author: liyaqing
 * @Date: Create in 5:25 下午 2022/9/20
 * @Description:
 */
@Composable
fun GoTheaterScreen(){
    Row(
       modifier = Modifier.fillMaxSize().padding(100.dp),
        verticalAlignment = Alignment.Top, // 垂直居中
        horizontalArrangement = Arrangement.SpaceBetween // 水平方向: 前后没有空隙，且子view之间均匀分散
    ) {
        // 添加背景来区分
        Text(text = "text1",modifier = Modifier.background(Red))
        Text(text = "text1",modifier = Modifier.background(Green))
        Text(text = "text1",modifier = Modifier.background(Green))
    }

}