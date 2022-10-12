package com.liyaqing.mycompose.network

/**
 * @Author: liyaqing
 * @Date: Create in 2:29 下午 2022/9/22
 * @Description:
 */
data class ApiBean<T>(
    val code:Int,
    val message:String,
    val data:T
)
