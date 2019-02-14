package com.rogertsai.mymvvm

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.rogertsai.mymvvm.databinding.MainActivityBinding
import com.rogertsai.mymvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mBinding: MainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        mMainViewModel = MainViewModel()
        mBinding.viewModel = mMainViewModel
    }
}
