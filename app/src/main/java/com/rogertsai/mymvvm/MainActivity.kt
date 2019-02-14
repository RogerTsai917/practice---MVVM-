package com.rogertsai.mymvvm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.rogertsai.mymvvm.databinding.MainActivityBinding
import com.rogertsai.mymvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mMainViewModel: MainViewModel

    private lateinit var mBinding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        init()
    }

    private fun init() {
        /* 不再用new而是改成透過ViewModelProviders協助我們取得ViewModel，
           其中of()的參數代表著ViewModel的生命範圍(scope)，
           在MainActivity中用of(this)表示ViewModel的生命週期會持續到MainActivity不再活動(destroy且沒有re-create)為止，
           只要MainActivity還在活動中，ViewModel就不會被清除，每次create都可以取得同一個ViewModel。 */
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mBinding.viewModel = mMainViewModel

        //使用observe(owner, Observer)來接收callback，owner用this表示LiveData會遵照MainActivity的生命週期判斷是否發送變更。
        mMainViewModel.mData.observe(this, Observer {data ->
            mBinding.textView.text = data
        })

        mMainViewModel.mToastText.observe(this, Observer { message ->
            showToast(message!!)
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
