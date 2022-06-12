package com.example.android_clean_architecture.ui.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import com.example.android_clean_architecture.ui.feature.base.BaseActivity
import com.example.android_clean_architecture.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindLayout: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        // -a-
    }
}