package com.example.finalproject_rockpaperscissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalproject_rockpaperscissors.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var extra: String
    private val txtUser by lazy { binding.txtUsername}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_game)
        setContentView(binding.root)

        extra = intent.getStringExtra(USER) ?: getString(R.string.UserName)
        txtUser.text = extra
    }
}