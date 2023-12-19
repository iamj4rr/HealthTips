package com.example.healthtips

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DataEditActivity : AppCompatActivity() {

    private lateinit var myButton : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data_edit)

        myButton = findViewById(R.id.save_data)
        myButton.setOnClickListener {
            val intent : Intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}