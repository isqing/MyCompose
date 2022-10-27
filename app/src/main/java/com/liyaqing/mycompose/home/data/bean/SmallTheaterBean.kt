package com.liyaqing.mycompose.home.data.bean

/**
 * @Author: liyaqing
 * @Date: Create in 2:18 下午 2022/9/22
 * @Description:
 */
data class HomeBean(
    val smallTheaterAllBean: SmallTheaterAllBean

)
data class SmallTheaterAllBean(
    val banner: SmallTheaterBean,
    val hot: SmallTheaterBeanList,
    )
data class SmallTheaterBean(
    val cover: String,
    val cover_height: Int,
    val cover_width: Int,
    val deep_link: String,
    val id: Int

)
data class SmallTheaterBeanList(
    val list: List<TheaterNodeItemBean>,
    val title: String,
)
    data class TheaterNodeItemBean(
    val cover: String,
    val cover_height: Int,
    val cover_width: Int,
    val episode_num: Int,
    val id: Int,
    val title: String,
    val update_status: Int,
    val update_text: String
)
