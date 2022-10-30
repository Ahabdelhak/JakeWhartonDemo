package com.demo.reposData.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.reposData.common.viewBinding
import com.demo.reposData.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}