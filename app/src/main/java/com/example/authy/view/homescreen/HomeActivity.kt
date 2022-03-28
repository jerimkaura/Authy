package com.example.authy.view.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.authy.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}