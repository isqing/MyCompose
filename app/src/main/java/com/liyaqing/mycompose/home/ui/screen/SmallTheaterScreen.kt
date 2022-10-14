package com.liyaqing.mycompose.home.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.liyaqing.mycompose.home.data.SmallTheaterViewModel
import com.liyaqing.mycompose.home.data.bean.SmallTheaterBean
import com.liyaqing.mycompose.home.data.bean.SmallTheaterBeanList
import com.liyaqing.mycompose.home.ui.banner.BannerScreen

/**
 * @Author: liyaqing
 * @Date: Create in 5:25 下午 2022/9/20
 * @Description:
 */
@Composable
fun SmallTheaterScreen(
    viewModel: SmallTheaterViewModel,

    ) {
    val hot = viewModel.hots.collectAsState().value
    val banners = viewModel.bannerDatas.collectAsState(initial = null).value
    Column(
        Modifier
            .background(Color.Black)
            .fillMaxSize()

    ) {
        banners?.let {
            BannerShow(banners = banners)
        }

        hot?.let {
            HotSmallTheaterScreenShow(hot = hot)
        }
    }


}

@Composable
private fun BannerShow(
    banners: List<SmallTheaterBean>,
    indicatorAlignment: Alignment = Alignment.BottomCenter,
) {
    Column() {
        BannerScreen(banners, indicatorAlignment)
    }
}

@Composable
private fun HotSmallTheaterScreenShow(
    hot: SmallTheaterBeanList?
) {

    Column() {
        Log.d("qing==", "SmallTheaterScreenshow: " + hot?.title)
        val title: String? = hot?.title;
        Text(
            text = "ppp$title", modifier = Modifier.background(Color.Green).offset(x=50.dp)
        )
    }
}