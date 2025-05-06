package com.example.recyclerviewkotlinexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recyclerviewkotlinexample.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {
    lateinit var binding: ActivityLandingBinding
    lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnTask1.setOnClickListener(){
            intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }

        binding.btnTask2.setOnClickListener(){
            intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}