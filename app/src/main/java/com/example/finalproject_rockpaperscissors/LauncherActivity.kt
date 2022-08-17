package com.example.finalproject_rockpaperscissors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalproject_rockpaperscissors.databinding.ActivityLauncherBinding
import com.google.android.material.snackbar.Snackbar

class LauncherActivity : AppCompatActivity() {
    private var wincounter:Int = 0
    private var boolh = 0
    private lateinit var binding: ActivityLauncherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStartLauncher.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        if(boolh.equals(0))
        {
            boolh = intent.getIntExtra("CT", 0)
            if(boolh.equals(2)) {
                wincounter = intent.getIntExtra(RETURN_WIN, 0)
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "You have won $wincounter time(s)",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//            if(resultCode == RESULT_OK && requestCode == REQUEST_CODE)
//            {
//                wincounter = data?.getIntExtra(RETURN_WIN, 0)!!
//                Snackbar.make(findViewById(android.R.id.content),"You have won $wincounter time(s)", Snackbar.LENGTH_LONG).show()
//            }
//    }
}