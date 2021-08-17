package com.saj.marvel.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.saj.marvel.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}