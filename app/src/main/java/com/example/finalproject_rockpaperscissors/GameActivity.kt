package com.example.finalproject_rockpaperscissors

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.finalproject_rockpaperscissors.databinding.ActivityGameBinding
import com.example.finalproject_rockpaperscissors.viewModel.GameViewModel

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private var shopUsername: String = ""
    private lateinit var extra: String
    private var diffExtra: Int = 0
    private var choice = 0
    private var lives = 0
    private var rockClicked = false
    private var paperClicked = false
    private var scissorsClicked = false
    private val btn_Shop by lazy {binding.btnShop}
    private val btn_Rock by lazy {binding.btnRock}
    private val btn_Paper by lazy {binding.btnPaper}
    private val btn_Scissors by lazy {binding.btnScissors}
    private val btn_Go by lazy {binding.btnGo}
    private val txtUser by lazy { binding.txtUsername}
    private val imageViewAI by lazy {binding.imageViewAI}
    private val imageViewUser by lazy {binding.imageViewUser}
    private val txtLives by lazy{binding.txtLives}
    private val txtCoins by lazy{binding.txtCoins}
    private val txtResult by lazy{binding.txtResult}
    val viewModel by viewModels<GameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_game)
        setContentView(binding.root)

        val shop_username = intent.getStringExtra("TEMP_USERNAME")
        val shop_coins = intent.getStringExtra("TEMP_COINS")
        val shop_lives = intent.getStringExtra("TEMP_LIVES")


        btn_Shop.isClickable = false
        btn_Shop.isEnabled = false
        diffExtra = intent.getIntExtra(DIFF, 0)
        extra = intent.getStringExtra(USER) ?: getString(R.string.UserName)
        txtUser.text = extra
        txtUser.text = shop_username
        txtLives.text = shop_coins
        txtLives.text = shop_lives
        viewModel.username.value = extra
        viewModel.username.observe(this){
            txtUser.text = it
        }
        viewModel.result.observe(this){
            txtResult.text = it
        }
        viewModel.imageViewUser.observe(this){
            imageViewUser.setImageDrawable(it)
        }
        viewModel.imageViewAI.observe(this){
            imageViewAI.setImageDrawable(it)
        }
        viewModel.livesLeft.observe(this){
            txtLives.text = "Lives: $it"

        }
        viewModel.coinsLeft.observe(this){
            txtCoins.text = "Coins: $it"

        }
        Algo()
        Compare()
    }
    private fun Algo(){

        btn_Go.isEnabled = false
        btn_Go.isClickable = false
        btn_Rock.setOnClickListener {
            rockClicked = true
            if(rockClicked == true){
                choice = 1
                btn_Go.isEnabled = true
                btn_Go.isClickable = true
                viewModel.imageViewUser.value = ContextCompat.getDrawable(this, R.drawable.rock)
                imageViewUser.setImageDrawable(viewModel.imageViewUser.value)
            }
        }
        btn_Paper.setOnClickListener {
            paperClicked = true
            if(paperClicked == true){
                choice = 2
                btn_Go.isEnabled = true
                btn_Go.isClickable = true
                viewModel.imageViewUser.value = ContextCompat.getDrawable(this, R.drawable.paper)
                imageViewUser.setImageDrawable(viewModel.imageViewUser.value)
            }
        }
        btn_Scissors.setOnClickListener {
            scissorsClicked = true
            if(scissorsClicked == true){
                choice = 3
                btn_Go.isEnabled = true
                btn_Go.isClickable = true
                viewModel.imageViewUser.value = ContextCompat.getDrawable(this, R.drawable.scissors)
                imageViewUser.setImageDrawable(viewModel.imageViewUser.value)
            }
        }

    }


    private fun checkLives(){
        val r = viewModel.livesLeft.value!!
        if(r <= 0){
            btn_Shop.isEnabled = true
            btn_Shop.isClickable = true
            btn_Go.isClickable = false
            btn_Go.isEnabled = false
            btn_Paper.isEnabled = false
            btn_Paper.isClickable = false
            btn_Scissors.isEnabled = false
            btn_Scissors.isClickable = false
            btn_Rock.isEnabled = false
            btn_Rock.isClickable = false
        }
        btn_Shop.setOnClickListener{
            val intent = Intent(this, ShopActivity::class.java)
            intent.putExtra("COINS", txtCoins.text.toString())
            intent.putExtra("LIVES", txtLives.text.toString())
            intent.putExtra("USERNAME", txtUser.text.toString())
            startActivity(intent)
        }
    }


    private fun Compare(){
        btn_Go.setOnClickListener {
            if(choice == 1){
                val random = (1..4).random()
                if(random == 1){
                    viewModel.imageViewAI.value = ContextCompat.getDrawable(this, R.drawable.rock)
                    imageViewAI.setImageDrawable(viewModel.imageViewAI.value)
                    viewModel.result.value = "Result: TIE. No coins/lives won/deducted."
                    txtResult.text = viewModel.result.value
//                    txtLives.text = "TABLA"
                }
                else if(random == 2){
                    viewModel.imageViewAI.value = ContextCompat.getDrawable(this, R.drawable.paper)
                    imageViewAI.setImageDrawable(viewModel.imageViewAI.value)
                    if(diffExtra.equals(3))
                    {
                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(3)
                        viewModel.result.value = "Result: DEFEATED. 3 lives deducted"
                    }
                    else if(diffExtra.equals(2))
                    {
                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(2)
                        viewModel.result.value = "Result: DEFEATED. 2 lives deducted"
                    }
                    else
                    {
                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(1)
                        viewModel.result.value = "Result: DEFEATED. 1 lives deducted"
                    }
                    txtResult.text = viewModel.result.value
                    checkLives()
//                    txtLives.text = "TALO"
                }
                else{
                    viewModel.imageViewAI.value = ContextCompat.getDrawable(this, R.drawable.scissors)
                    imageViewAI.setImageDrawable(viewModel.imageViewAI.value)
                    lives = 3
                    viewModel.coinsLeft.value = viewModel.coinsLeft.value?.plus(5)
                    viewModel.result.value = "Result: VICTORY. 5 points won"
                    txtResult.text = viewModel.result.value
//                    txtLives.text = "PANALO"
                }
            }
            else if(choice == 2){
                val random = (1..4).random()
                if(random == 1){
                    viewModel.imageViewAI.value = ContextCompat.getDrawable(this, R.drawable.rock)
                    imageViewAI.setImageDrawable(viewModel.imageViewAI.value)
                    viewModel.coinsLeft.value = viewModel.coinsLeft.value?.plus(5)
                    viewModel.result.value = "Result: VICTORY. 5 points won"
                    txtResult.text = viewModel.result.value
//                    txtLives.text = "PANALO"
                }
                else if(random == 2){
                    viewModel.imageViewAI.value = ContextCompat.getDrawable(this, R.drawable.paper)
                    imageViewAI.setImageDrawable(viewModel.imageViewAI.value)
                    viewModel.result.value = "Result: TIE. No coins/lives won/deducted."
                    txtResult.text = viewModel.result.value
//                    txtLives.text = "TABLE"
                }
                else{
                    viewModel.imageViewAI.value = ContextCompat.getDrawable(this, R.drawable.scissors)
                    imageViewAI.setImageDrawable(viewModel.imageViewAI.value)
                    if(diffExtra.equals(3))
                    {
                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(3)
                        viewModel.result.value = "Result: DEFEATED. 3 lives deducted"
                    }
                    else if(diffExtra.equals(2))
                    {
                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(2)
                        viewModel.result.value = "Result: DEFEATED. 2 lives deducted"
                    }
                    else
                    {
                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(1)
                        viewModel.result.value = "Result: DEFEATED. 1 lives deducted"
                    }
                    txtResult.text = viewModel.result.value
//                    txtLives.text = "TALO"
                    checkLives()
                }
            }
            else if(choice == 3){
                val random = (1..4).random()
                if(random == 1){
                    viewModel.imageViewAI.value = ContextCompat.getDrawable(this, R.drawable.rock)
                    imageViewAI.setImageDrawable(viewModel.imageViewAI.value)
                    if(diffExtra.equals(3))
                    {
                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(3)
                        viewModel.result.value = "Result: DEFEATED. 3 lives deducted"
                    }
                    else if(diffExtra.equals(2))
                    {
                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(2)
                        viewModel.result.value = "Result: DEFEATED. 2 lives deducted"
                    }
                    else
                    {
                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(1)
                        viewModel.result.value = "Result: DEFEATED. 1 lives deducted"
                    }
                    txtResult.text = viewModel.result.value
//                    txtLives.text = "TALO"
                    checkLives()
                }
                else if(random == 2){
                    viewModel.imageViewAI.value = ContextCompat.getDrawable(this, R.drawable.paper)
                    imageViewAI.setImageDrawable(viewModel.imageViewAI.value)
                    viewModel.coinsLeft.value = viewModel.coinsLeft.value?.plus(5)
                    viewModel.result.value = "Result: VICTORY. 5 points won"
                    txtResult.text = viewModel.result.value
//                    txtLives.text = "PANALO"
                }
                else{
                    viewModel.imageViewAI.value = ContextCompat.getDrawable(this, R.drawable.scissors)
                    imageViewAI.setImageDrawable(viewModel.imageViewAI.value)
                    viewModel.result.value = "Result: TIE. No coins/lives won/deducted."
                    txtResult.text = viewModel.result.value
//                    txtLives.text = "TABLA"
                }
            }
            btn_Go.isEnabled = false
            btn_Go.isClickable = false
        }


    }

}