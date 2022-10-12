package com.liyaqing.mycompose.home.data

import androidx.lifecycle.viewModelScope
import com.liyaqing.mycompose.home.data.bean.SmallTheaterBean
import com.liyaqing.mycompose.home.data.bean.SmallTheaterBeanList
import com.liyaqing.mycompose.network.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * @Author: liyaqing
 * @Date: Create in 11:35 上午 2022/9/22
 * @Description:
 */
class SmallTheaterViewModel : BaseViewModel() {

    val hots by lazy {
        MutableStateFlow<SmallTheaterBeanList>(
            SmallTheaterBeanList(
                emptyList(),
                ""
            )
        )
    }
    val bannerDatas by lazy { MutableStateFlow<List<SmallTheaterBean>>(emptyList())}

    private val repository by lazy { SmallTheaterRepository() }

    init {
        loadHotDatas()
    }

    private fun loadHotDatas() {
        viewModelScope.launch {
            val result = async { repository.getSmallTheaterData() }
            val result1 =  async{repository.getBannerData()}

            hots.value = result.await();
            bannerDatas.value=result1.await()

        }
        // Do an asynchronous operation to fetch users.
    }

    private fun loadBannerDatas() {
        viewModelScope.launch {
            val result = repository.getBannerData()
            bannerDatas.value = result
        }
        // Do an asynchronous operation to fetch users.
    }

}