package com.example.finalproject_rockpaperscissors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.finalproject_rockpaperscissors.databinding.ActivityShopBinding
import com.example.finalproject_rockpaperscissors.viewModel.GameViewModel


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
        val coins = intent.getStringExtra("COINS")
        val lives = intent.getStringExtra("LIVES")


        viewUsername!!.text = username
        viewCoins!!.text = coins
        viewLives!!.text = lives





        binding.buttonBuy.setOnClickListener{
            val intent = Intent(this, GameActivity::class.java)

            var tempCoins: Int = viewCoins.toString().toInt()
            var tempLives: Int = viewLives.toString().toInt()

            viewModel.newLives.value?.plus(viewLives.toString().toInt() + 5)

            tempCoins -= 5
//          tempLives += 3

            intent.putExtra("TEMP_COINS", tempCoins.toString())
//            intent.putExtra("TEMP_LIVES", tempLives.toString())
            intent.putExtra("TEMP_USERNAME", viewUsername!!.text.toString())
            startActivity(intent)
        }

        binding.buttonMenu.setOnClickListener{
            val intent = Intent(this, LauncherActivity::class.java)
            startActivity(intent)
        }



    }
}