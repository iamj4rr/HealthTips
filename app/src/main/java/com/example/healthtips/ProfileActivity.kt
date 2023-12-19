package com.example.healthtips

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.healthtips.`object`.global

class ProfileActivity : AppCompatActivity() {

    private lateinit var myButton : ImageView
    private lateinit var myButton2 : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        val sharedPref: SharedPreferences = this@ProfileActivity.getSharedPreferences(global.Pref_Name,MODE_PRIVATE)
        val user_name = sharedPref.getString("username","").toString()
        val nama_user : TextView = findViewById(R.id.user_nama2)
        nama_user.text = user_name
        
        myButton = findViewById(R.id.back)
        myButton.setOnClickListener {
            val intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        myButton2 = findViewById(R.id.edit_button)
        myButton2.setOnClickListener {
            val intent : Intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
    }
}