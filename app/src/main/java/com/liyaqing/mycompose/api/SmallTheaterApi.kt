package com.liyaqing.mycompose.api

import com.liyaqing.mycompose.home.data.bean.SmallTheaterBean
import com.liyaqing.mycompose.home.data.bean.SmallTheaterBeanList
import com.liyaqing.mycompose.network.ApiBean
import retrofit2.Call
import retrofit2.http.GET

/**
 * @Author: liyaqing
 * @Date: Create in 2:45 下午 2022/9/22
 * @Description:
 */
interface SmallTheaterApi {
    @GET("/x-pgc/shortplay/index/node1List")
    suspend fun getSmallTheaterData(): ApiBean<SmallTheaterBeanList>
    @GET("/x-pgc/shortplay/banner/list")
    suspend fun getBannerData(): ApiBean<List<SmallTheaterBean>>
}