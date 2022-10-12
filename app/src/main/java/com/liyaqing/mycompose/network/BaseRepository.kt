package com.liyaqing.mycompose.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @Author: liyaqing
 * @Date: Create in 3:49 下午 2022/9/22
 * @Description:
 */
open class BaseRepository {
    suspend fun <T : Any> request(call: suspend () -> ApiBean<T>): ApiBean<T> {
        return withContext(Dispatchers.IO){ call.invoke()}
    }
}