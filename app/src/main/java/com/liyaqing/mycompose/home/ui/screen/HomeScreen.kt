package com.liyaqing.mycompose.home.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.liyaqing.mycompose.home.data.SmallTheaterViewModel

/**
 * @Author: liyaqing
 * @Date: Create in 4:38 下午 2022/9/20
 * @Description:
 */
@Composable
fun HomeScreen(
    mOwner: LifecycleOwner,
) {
    HomeNavigation(mOwner);
}