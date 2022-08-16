package com.example.finalproject_rockpaperscissors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.finalproject_rockpaperscissors.databinding.ActivityLauncherBinding
import com.example.finalproject_rockpaperscissors.databinding.ActivityMainBinding
import com.example.finalproject_rockpaperscissors.viewModel.GameViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var UserName: String = ""
    private val User by lazy { binding.etxtUser }
    private var Diff: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.bttnEasy.setOnClickListener {
            if (User.text.isNotEmpty()) {
                UserName = User.text.toString()
                val intent = Intent(this, GameActivity::class.java)
                Diff = 1
                intent.putExtra(DIFF, Diff)
                intent.putExtra(USER, UserName)
                startActivity(intent)
            } else {
                Snackbar.make(it, "Enter Your UserName", Snackbar.LENGTH_LONG).show()
            }
        }
        binding.bttnNorm.setOnClickListener {
            if (User.text.isNotEmpty()) {
                UserName = User.text.toString()
                val intent = Intent(this, GameActivity::class.java)
                Diff = 2
                intent.putExtra(DIFF, Diff)
                intent.putExtra(USER, UserName)
                startActivity(intent)
            } else {
                Snackbar.make(it, "Enter Your UserName", Snackbar.LENGTH_LONG).show()
            }
        }
        binding.bttnHard.setOnClickListener {
            if (User.text.isNotEmpty()) {
                UserName = User.text.toString()
                val intent = Intent(this, GameActivity::class.java)
                Diff = 3
                intent.putExtra(DIFF, Diff)
                intent.putExtra(USER, UserName)
                startActivity(intent)
            } else {
                Snackbar.make(it, "Enter Your UserName", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}