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
    private var lol: String = "Lives: "
    private var diffExtra: Int = 0
    private var choice = 0
    private var lives = 0
    private var hp = 5
    private var coins = 5
    private var chcker = 0
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


        val shop_coins = intent.getStringExtra("TEMP_COINS")
        val shop_lives = intent.getStringExtra("TEMP_LIVES")
        chcker = intent.getIntExtra("checkthis", 0)



        btn_Shop.isClickable = false
        btn_Shop.isEnabled = false

        extra = intent.getStringExtra(USER) ?: getString(R.string.UserName)

        val shop_username = intent.getStringExtra("TEMP_USERNAME")
        txtUser.text = shop_username.toString()
        txtCoins.text = shop_coins
        txtLives.text =  shop_lives
        if(chcker.equals(0)) {
            diffExtra = intent.getIntExtra(DIFF, 0)
            txtLives.text = hp.toString()
            txtCoins.text = coins.toString()
            viewModel.username.value = extra

        }
        if(chcker.equals(2))
        {
            hp = shop_lives!!.toInt()
            coins = shop_coins!!.toInt()
            viewModel.username.value = shop_username
            val shop_diff = intent.getIntExtra("TEMP_DIFF", 0)
            diffExtra = shop_diff
        }

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
//        viewModel.livesLeft.observe(this){
//            txtLives.text = "Lives: $it"
//
//        }
//        viewModel.coinsLeft.observe(this){
//            txtCoins.text = "Coins: $it"
//
//        }
        Algo()
        Compare()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE)
//        {
//            hp = data?.getIntExtra(RETURN_KEY, 5)!!
//            coins = data?.getIntExtra(HATDOG_KEY, 5)!!
//
//            txtCoins.setText(coins)
//            txtLives.setText(hp)
//        }
//    }
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
        val r = hp
        if(r < 1){
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
            hp = 0
            txtLives.text = hp.toString()
        }
        btn_Shop.setOnClickListener{
            val intent = Intent(this, ShopActivity::class.java)
            intent.putExtra("COINS", txtCoins.text.toString().toInt())
            intent.putExtra("LIVES", txtLives.text.toString().toInt())
            intent.putExtra("USERNAME", txtUser.text.toString())
            intent.putExtra("DIFDIF", diffExtra)
            startActivity(intent)
        }
    }


    private fun Compare(){
        checkLives()
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
//                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(3)
                        hp -= 3

                        viewModel.result.value = "Result: DEFEATED. 3 lives deducted"
                    }
                    else if(diffExtra.equals(2))
                    {
                        hp -= 2

//                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(2)
                        viewModel.result.value = "Result: DEFEATED. 2 lives deducted"
                    }
                    else
                    {
                        hp -= 1

//                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(1)
                        viewModel.result.value = "Result: DEFEATED. 1 lives deducted"
                    }
                    txtLives.text = hp.toString()
                    txtResult.text = viewModel.result.value
                    checkLives()
//                    txtLives.text = "TALO"
                }
                else{
                    viewModel.imageViewAI.value = ContextCompat.getDrawable(this, R.drawable.scissors)
                    imageViewAI.setImageDrawable(viewModel.imageViewAI.value)
                    lives = 3
//                    viewModel.coinsLeft.value = viewModel.coinsLeft.value?.plus(5)
                    coins += 5
                    txtCoins.text = coins.toString()
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
//                    viewModel.coinsLeft.value = viewModel.coinsLeft.value?.plus(5)
                    lives = 3
                    coins += 5
                    viewModel.result.value = "Result: VICTORY. 5 points won"
                    txtResult.text = viewModel.result.value
                    txtCoins.text = coins.toString()
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
//                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(3)
                        hp -= 3
                        viewModel.result.value = "Result: DEFEATED. 3 lives deducted"
                    }
                    else if(diffExtra.equals(2))
                    {
//                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(2)
                        hp -= 2
                        viewModel.result.value = "Result: DEFEATED. 2 lives deducted"
                    }
                    else
                    {
//                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(1)
                        hp -= 1
                        viewModel.result.value = "Result: DEFEATED. 1 lives deducted"
                    }
                    txtLives.text = hp.toString()
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
//                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(3)
                        hp -= 3
                        viewModel.result.value = "Result: DEFEATED. 3 lives deducted"
                    }
                    else if(diffExtra.equals(2))
                    {
//                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(2)
                        hp -= 2
                        viewModel.result.value = "Result: DEFEATED. 2 lives deducted"
                    }
                    else
                    {
//                        viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(1)
                        hp -= 1
                        viewModel.result.value = "Result: DEFEATED. 1 lives deducted"
                    }
                    txtLives.text = hp.toString()
                    txtResult.text = viewModel.result.value
//                    txtLives.text = "TALO"
                    checkLives()
                }
                else if(random == 2){
                    viewModel.imageViewAI.value = ContextCompat.getDrawable(this, R.drawable.paper)
                    imageViewAI.setImageDrawable(viewModel.imageViewAI.value)
//                    viewModel.coinsLeft.value = viewModel.coinsLeft.value?.plus(5)
                    lives = 3
                    coins += 5
                    viewModel.result.value = "Result: VICTORY. 5 points won"
                    txtResult.text = viewModel.result.value
                    txtCoins.text = coins.toString()
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