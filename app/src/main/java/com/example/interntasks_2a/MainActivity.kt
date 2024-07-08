package com.example.interntasks_2a

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.interntasks_2a.case1.ListActivity
import com.example.interntasks_2a.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGoCase1.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
    }
}