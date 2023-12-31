package com.liyaqing.mycompose.home.ui.banner

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.liyaqing.mycompose.home.data.bean.SmallTheaterBean
import kotlinx.coroutines.delay

/**
 * @Author: liyaqing
 * @Date: Create in 10:53 上午 2022/10/12
 * @Description:
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerScreen(
    banners: List<SmallTheaterBean>,
    indicatorAlignment: Alignment = Alignment.BottomCenter
) {
    val pagerState = rememberPagerState(
        //总页数
        pageCount = banners.size,
        //预加载的个数
        initialOffscreenLimit = 1,
        //是否无限循环
        infiniteLoop = true,
        //初始页面
        initialPage = 0
    )
    //监听动画执行
    var executeChangePage by remember { mutableStateOf(false) }
    var currentPageIndex = 0
    //自动滚动
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.pageCount > 0) {
            delay(3000)
            pagerState.animateScrollToPage(pagerState.currentPage + 1)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.pointerInput(pagerState.currentPage) {
            awaitPointerEventScope {
                while (true) {
                    //PointerEventPass.Initial - 本控件优先处理手势，处理后再交给子组件
                    val event = awaitPointerEvent(PointerEventPass.Initial)
                    //获取到第一根按下的手指
                    val dragEvent = event.changes.firstOrNull()
                    when {
                        //当前移动手势是否已被消费
                        dragEvent!!.positionChangeConsumed() -> {
                            return@awaitPointerEventScope
                        }
                        //是否已经按下(忽略按下手势已消费标记)
                        dragEvent.changedToDownIgnoreConsumed() -> {
                            //记录下当前的页面索引值
                            currentPageIndex = pagerState.currentPage
                        }
                        //是否已经抬起(忽略按下手势已消费标记)
                        dragEvent.changedToUpIgnoreConsumed() -> {
                            //当页面没有任何滚动/动画的时候pagerState.targetPage为null，这个时候是单击事件
                            if (pagerState.targetPage == null) return@awaitPointerEventScope
                            //当pageCount大于1，且手指抬起时如果页面没有改变，就手动触发动画
                            if (currentPageIndex == pagerState.currentPage && pagerState.pageCount > 1) {
                                executeChangePage = !executeChangePage
                            }
                        }
                    }
                }
            }
        }
//            .clickable(onClick = { onClick(list[pagerState.currentPage].linkUrl) })
            .fillMaxWidth()
            .height(150.dp)
            .padding(0.dp, 5.dp, 0.dp, 0.dp),

        ) { page ->
        AsyncImage(
            model=banners[page].cover,
//            painter = rememberImagePainter(banners[page].cover),
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Box(
            modifier = Modifier.align(indicatorAlignment)
                .padding(bottom = 6.dp, start = 6.dp, end = 6.dp)
        ) {

            //指示点
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in banners.indices) {
                    //大小
                    var size by remember { mutableStateOf(5.dp) }
                    size = if (pagerState.currentPage == i) 7.dp else 5.dp

                    //颜色
                    val color =
                        if (pagerState.currentPage == i) MaterialTheme.colors.primary else Color.Gray

                    Box(
                        modifier = Modifier.clip(CircleShape).background(color)
                            //当size改变的时候以动画的形式改变
                            .animateContentSize().size(size)
                    )
                    //指示点间的间隔
                    if (i != banners.lastIndex) Spacer(
                        modifier = Modifier.height(0.dp).width(4.dp)
                    )
                }
            }
        }


    }

}