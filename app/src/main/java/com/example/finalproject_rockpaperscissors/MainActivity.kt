package com.example.finalproject_rockpaperscissors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalproject_rockpaperscissors.databinding.ActivityLauncherBinding
import com.example.finalproject_rockpaperscissors.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var UserName: String = ""
    private val User by lazy { binding.etxtUser}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)


        binding.bttnEasy.setOnClickListener{
            UserName = User.text.toString()
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(USER, UserName)
            startActivity(intent)
        }
        binding.bttnNorm.setOnClickListener {
            UserName = User.text.toString()
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(USER, UserName)
            startActivity(intent)
        }
        binding.bttnHard.setOnClickListener {
            UserName = User.text.toString()
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(USER, UserName)
            startActivity(intent)
        }
    }
}
