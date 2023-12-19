package com.example.healthtips

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.healthtips.`object`.global


class GetStartedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.get_started)
            val myButton : Button = findViewById(R.id.buttonGetStarted)
            myButton.setOnClickListener {
                val intent : Intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
    }

    }
    override fun onStart(){
        super.onStart()
        val sharedPref: SharedPreferences = this@GetStartedActivity.getSharedPreferences(global.Pref_Name,MODE_PRIVATE)
        if(sharedPref.getBoolean("login_session",false)){
            moveIntent()
        }
    }

    private fun moveIntent(){
        val intent : Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
