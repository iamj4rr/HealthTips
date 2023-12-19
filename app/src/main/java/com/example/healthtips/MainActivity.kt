package com.example.healthtips

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.healthtips.`object`.global
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var myButton : LinearLayout
    private lateinit var myButton2 : LinearLayout
    private lateinit var myButton3 : LinearLayout

    private var urlSignIn : String ="http://iamj4rr.my.id/get_data_tabel_user.php"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)


        val sharedPref: SharedPreferences = this@MainActivity.getSharedPreferences(global.Pref_Name,MODE_PRIVATE)
        val id = sharedPref.getString("id","").toString()


        val stringRequest: StringRequest = object : StringRequest(
            Method.POST,
            urlSignIn,
            object : com.android.volley.Response.Listener<String?> {
                override fun onResponse(response: String?) {
                    try {
                        val editText : TextView = findViewById(R.id.user_nama)
                        val jsonObject = JSONObject(response)
                        val nama_user = jsonObject.getString("username")
                        editText.text = nama_user
                    }catch (e : JSONException) {
                        e.printStackTrace();
                    }
                }
            },
            object : com.android.volley.Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {
                    Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): HashMap<String, String>? {
                val params = HashMap<String, String>()
                params["post_id"] = id
                return params
            }
        }
        val requestQueue = Volley.newRequestQueue(
            applicationContext
        )
        requestQueue.add(stringRequest)


        val imageButton : ImageButton=findViewById(R.id.button_profile)
        imageButton.setOnClickListener {
            val intent :  Intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        myButton = findViewById(R.id.button_article2)
        myButton.setOnClickListener {
            val intent : Intent = Intent(this, ArticleActivity::class.java)
            startActivity(intent)
        }

        myButton2 = findViewById(R.id.button_vidio2)
        myButton2.setOnClickListener {
            val intent : Intent = Intent(this, VideoActivity::class.java)
            startActivity(intent)
        }

        myButton3 = findViewById(R.id.button_apps2)
        myButton3.setOnClickListener {
            val intent : Intent = Intent(this, AppsActivity::class.java)
            startActivity(intent)
        }
    }
}

