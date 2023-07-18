package com.liyaqing.mycompose.home.data

import com.liyaqing.mycompose.api.SmallTheaterApi
import com.liyaqing.mycompose.home.data.bean.SmallTheaterBean
import com.liyaqing.mycompose.home.data.bean.SmallTheaterBeanList
import com.liyaqing.mycompose.network.ApiBean
import com.liyaqing.mycompose.network.BaseRepository
import com.liyaqing.mycompose.network.Network

/**
 * @Author: liyaqing
 * @Date: Create in 3:09 下午 2022/9/23
 * @Description:
 */
class SmallTheaterRepository : BaseRepository() {
    suspend fun getSmallTheaterData(): SmallTheaterBeanList {
        val smallTheaterBeanList= Network.retrofit.create(SmallTheaterApi::class.java)
            .getSmallTheaterData()
        return smallTheaterBeanList.data;
    }
    suspend fun getBannerData(): List<SmallTheaterBean> {
        val bannerList= Network.retrofit.create(SmallTheaterApi::class.java)
            .getBannerData()
        return bannerList.data;
    }
}