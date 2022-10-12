package com.liyaqing.mycompose.home.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController

/**
 * @Author: liyaqing
 * @Date: Create in 5:25 下午 2022/9/20
 * @Description:
 */
@Composable
fun MineScreen(navController:NavController,mOwner: LifecycleOwner){
    Column() {
        Text(text = "Mine")
    }
    
}