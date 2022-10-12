package com.liyaqing.mycompose.network

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.TimeoutCancellationException


abstract class BaseViewModelActivity<VM : BaseViewModel> : ComponentActivity() {

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initVM()
        super.onCreate(savedInstanceState)
        startObserve()
    }

    private fun initVM() {
        providerVMClass()?.let {
            viewModel = ViewModelProvider(this)[it]
            lifecycle.addObserver(viewModel)
        }
    }

    open fun providerVMClass(): Class<VM>? = null

    private fun startObserve() {
        //处理一些通用异常，比如网络超时等
        viewModel.run {
            getError().observe(this@BaseViewModelActivity, Observer {
                requestError(it)
            })
            getFinally().observe(this@BaseViewModelActivity, Observer {
                requestFinally(it)
            })
        }
    }

    open fun requestFinally(it: Int?) {

    }

    open fun requestError(it: Exception?) {
        //处理一些已知异常
        it?.run {
            when (it) {
                is TimeoutCancellationException -> {
                    showToast("请求超时")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel)
    }
    protected fun showToast(msg:String){
        Toast.makeText(applicationContext,msg, Toast.LENGTH_SHORT).show()
    }
}