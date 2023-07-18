package com.liyaqing.mycompose.home.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.liyaqing.mycompose.R
import com.liyaqing.mycompose.home.data.SmallTheaterViewModel
import com.liyaqing.mycompose.home.data.bean.SmallTheaterBean
import com.liyaqing.mycompose.home.ui.banner.BannerScreen
import com.liyaqing.mycompose.home.ui.title.Titleview

/**
 * @Author: liyaqing
 * @Date: Create in 5:25 下午 2022/9/20
 * @Description:
 */
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
fun SmallTheaterScreen(
    viewModel: SmallTheaterViewModel
) {
    val hot = viewModel.hots.collectAsState().value
    val banners = viewModel.bannerDatas.collectAsState(initial = null).value
    LazyVerticalGrid( columns=  GridCells.Fixed(3),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)){
        item(span = { GridItemSpan(3) }) {
            banners?.let {
                BannerShow(banners = banners)
            }
        }
        item(span = { GridItemSpan(3) }) {
            val title: String? = hot?.title;
            Titleview(R.drawable.icon_hot, title)
        }
        items(count = hot.list.size, span = { GridItemSpan(1) }) {
            TheaterFaceItem(item = hot.list[it])
        }
        item(span = { GridItemSpan(3) }) {
            val title: String? = hot?.title;
            Titleview(R.drawable.icon_hot, title)
        }
        items(count = hot.list.size, span = { GridItemSpan(3) }) {
            TheaterListItem(item = hot.list[it])
        }
    }
}
