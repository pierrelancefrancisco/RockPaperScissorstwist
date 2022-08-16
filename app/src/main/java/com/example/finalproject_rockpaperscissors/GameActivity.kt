package com.example.finalproject_rockpaperscissors

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.finalproject_rockpaperscissors.databinding.ActivityGameBinding
import com.example.finalproject_rockpaperscissors.viewModel.GameViewModel

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var extra: String
    private var diffExtra: Int = 0
    private var choice = 0
    private var lives = 0
    private var rockClicked = false
    private var paperClicked = false
    private var scissorsClicked = false
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
        diffExtra = intent.getIntExtra(DIFF, 0)
        extra = intent.getStringExtra(USER) ?: getString(R.string.UserName)
        txtUser.text = extra
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
                    viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(1)
                    viewModel.result.value = "Result: DEFEATED. 1 lives deducted"
                    txtResult.text = viewModel.result.value
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
                    viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(1)
                    viewModel.result.value = "Result: DEFEATED. 1 lives deducted"
                    txtResult.text = viewModel.result.value
//                    txtLives.text = "TALO"
                }
            }
            else if(choice == 3){
                val random = (1..4).random()
                if(random == 1){
                    viewModel.imageViewAI.value = ContextCompat.getDrawable(this, R.drawable.rock)
                    imageViewAI.setImageDrawable(viewModel.imageViewAI.value)
                    viewModel.livesLeft.value = viewModel.livesLeft.value?.minus(1)
                    viewModel.result.value = "Result: DEFEATED. 1 lives deducted"
                    txtResult.text = viewModel.result.value
//                    txtLives.text = "TALO"
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