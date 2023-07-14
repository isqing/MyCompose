package com.liyaqing.mycompose.home.ui.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.liyaqing.mycompose.R
import com.liyaqing.mycompose.home.data.SmallTheaterViewModel
import com.liyaqing.mycompose.home.data.bean.SmallTheaterBean
import com.liyaqing.mycompose.home.data.bean.SmallTheaterBeanList
import com.liyaqing.mycompose.home.ui.banner.BannerScreen
import com.liyaqing.mycompose.home.ui.title.Titleview

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HotSmallTheaterScreenShow(
    hot: SmallTheaterBeanList?
) {

    Column(modifier = Modifier.padding(10.dp)) {
        Log.d("qing==", "SmallTheaterScreenshow: " + hot?.title)
        val title: String? = hot?.title;

        Titleview(R.drawable.icon_hot,title)

        hot?.let {
            LazyVerticalGrid(
                cells = GridCells.Fixed(3),
                modifier = Modifier.padding(top = 10.dp)

            ) {
                items(it.list) { item ->
                    TheaterFaceItem(item)
                }
            }
            LazyColumn(
                Modifier
                    .fillMaxSize()
            ) {
                items(items = hot.list,
                key = {
                        theaterNodeItemBean->theaterNodeItemBean.id
                }){
                    theaterNodeItemBean->
                    TheaterListItem(item = theaterNodeItemBean)
                }
                
            }

        }
    }
}