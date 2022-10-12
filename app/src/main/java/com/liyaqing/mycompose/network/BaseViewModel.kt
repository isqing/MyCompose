package com.liyaqing.mycompose.network

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.*
import kotlinx.coroutines.*
import java.lang.Exception

/**
 * @Author: liyaqing
 * @Date: Create in 3:51 下午 2022/9/22
 * @Description:
 */
open class BaseViewModel : ViewModel(), LifecycleObserver{
    private val error by lazy { MutableLiveData<java.lang.Exception>() }

    private val finally by lazy { MutableLiveData<Int>() }
    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //运行在UI线程的协程
    fun launchUI( block: suspend CoroutineScope.() -> Unit) {
        try {
            uiScope.launch(Dispatchers.Main) {
                block()
            }
        } catch (e: Exception) {
            error.value = e
        } finally {
            finally.value = 200
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    /**
     * 请求失败，出现异常
     */
    fun getError(): LiveData<java.lang.Exception> {
        return error
    }

    /**
     * 请求完成，在此处做一些关闭操作
     */
    fun getFinally(): LiveData<Int> {
        return finally
    }
}