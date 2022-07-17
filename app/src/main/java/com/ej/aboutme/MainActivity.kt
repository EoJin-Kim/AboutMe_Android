package com.ej.aboutme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.ej.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SystemClock.sleep(5000)

        setTheme(R.style.Theme_AboutMe)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}