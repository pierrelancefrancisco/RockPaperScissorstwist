package com.example.finalproject_rockpaperscissors

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.finalproject_rockpaperscissors.databinding.ActivityShopBinding
import com.example.finalproject_rockpaperscissors.viewModel.GameViewModel
import com.google.android.material.snackbar.Snackbar


class ShopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopBinding
    private val viewUsername by lazy{binding.txtName}
    private val viewCoins by lazy{binding.txtCoins}
    private val viewLives by lazy{binding.txtLives}

    val viewModel by viewModels<GameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("USERNAME")
        var coins = intent.getIntExtra("COINS",0)
        var lives = intent.getIntExtra("LIVES", 0)
        val Diff = intent.getIntExtra("DIFDIF", 0)
        val counter = intent.getIntExtra("WINCOUNTER", 0)


        viewUsername!!.text = username
        viewCoins!!.text = coins.toString()
        viewLives!!.text = lives.toString()





        binding.buttonBuy.setOnClickListener{
            val intent = Intent(this, GameActivity::class.java)

            var tempCoins: Int = coins.toString().toInt()
            var tempLives: Int = lives.toString().toInt()
            //viewModel.newLives.value?.plus(viewLives.toString().toInt() + 5)

            tempCoins -= 5
            tempLives += 5

            intent.putExtra("checkthis", 2)
            intent.putExtra("TEMP_COINS", tempCoins.toString())
            intent.putExtra("TEMP_LIVES", tempLives.toString())
            intent.putExtra("TEMP_USERNAME", username.toString())
            intent.putExtra("TEMP_DIFF", Diff)
            intent.putExtra(RETURN_WIN, counter)
            startActivity(intent)
        }

        binding.buttonMenu.setOnClickListener{
            val intent = Intent(this, LauncherActivity::class.java)
            val tb = 2
            intent.putExtra("CT", tb)
            intent.putExtra(RETURN_WIN, counter)
            setResult(Activity.RESULT_OK, intent)
            startActivity(intent)
        }



    }
}