package com.example.finalproject_rockpaperscissors.viewModel

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject_rockpaperscissors.databinding.ActivityGameBinding
import java.util.*


class GameViewModel: ViewModel() {
    private val _livesLeft = MutableLiveData<Int>()
    private val _coinsLeft = MutableLiveData<Int>()
    private val _username = MutableLiveData<String>()
    private val _result = MutableLiveData<String>()
    private val _imageViewAI = MutableLiveData<Drawable>()
    private val _imageViewUser = MutableLiveData<Drawable>()
    private val _newLives = MutableLiveData<Int>()
    var newLives = _newLives
    var livesLeft = _livesLeft
    var coinsLeft = _coinsLeft
    var username = _username
    var result = _result
    var imageViewAI = _imageViewAI
    var imageViewUser = _imageViewUser


    init{
        _livesLeft.value = 5
        _coinsLeft.value = 5
    }






}