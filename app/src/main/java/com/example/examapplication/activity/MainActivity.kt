package com.example.examapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examapplication.R
import com.example.examapplication.fragment.PhotoFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}