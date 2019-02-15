package com.rogertsai.mymvvm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.rogertsai.mymvvm.ui.RepoFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        init()
    }

    private fun init() {
        val tag = RepoFragment.TAG

        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            val fragment = RepoFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, fragment, tag)
                    .commit()
        }
    }
}
