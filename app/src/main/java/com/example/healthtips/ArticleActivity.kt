package com.example.healthtips

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
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

class ArticleActivity : AppCompatActivity() {

    private lateinit var  button : ImageView
    private var urlSignIn : String ="http://iamj4rr.my.id/get_data_tabel_user.php"
    private var url : String ="http://iamj4rr.my.id/get_image.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article)

        val sharedPref: SharedPreferences = this@ArticleActivity.getSharedPreferences(global.Pref_Name,MODE_PRIVATE)
        val editor =  sharedPref.edit()
        val id = sharedPref.getString("id","").toString()


        val stringRequest: StringRequest = object : StringRequest(
            Method.POST,
            urlSignIn,
            object : com.android.volley.Response.Listener<String?> {
                override fun onResponse(response: String?) {
                    try {
                        val jsonObject = JSONObject(response)
                        val berat = jsonObject.getString("berat")
                        val tinggi= jsonObject.getString("tinggi")
                        editor.putString("berat", berat.toString())
                        editor.putString("tinggi", tinggi.toString())
                        editor.apply()
                        main()
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
        val requestQueue = Volley.newRequestQueue(applicationContext)
        requestQueue.add(stringRequest)

        button= findViewById(R.id.backArticle)
        button.setOnClickListener {
            val intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    fun main(){
        val sharedPref: SharedPreferences = this@ArticleActivity.getSharedPreferences(global.Pref_Name,MODE_PRIVATE)
        val editor =  sharedPref.edit()
        val berat = sharedPref.getString("berat","")?.toFloat()
        val tinggi = sharedPref.getString("tinggi","")?.toFloat()
        var imk : Float = berat!!.div(tinggi!!.times(tinggi))
        var a = 25
        var b = 18.5

        val tv : TextView = findViewById(R.id.imt)
        tv.text = "(" + imk.toString() + ")"

        val link = sharedPref.getString("link 1", "").toString()
        val tv2 : TextView = findViewById(R.id.link_article)
        tv2.text = link

        if(imk.compareTo(a)>=0 ){
            val stringRequest: StringRequest = object : StringRequest(
                Method.POST,
                url,
                object : com.android.volley.Response.Listener<String?> {
                    override fun onResponse(response: String?) {
                        try {
                            val jsonObject = JSONObject(response)
                            val penjelasan = jsonObject.getString("gambar")
                            val link= jsonObject.getString("link_article")
                            editor.putString("penjelasan1", penjelasan.toString())
                            editor.putString("link 1", link.toString())
                            editor.apply()

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
                    params["post_id"] =
                    return params
                }
            }
            val requestQueue = Volley.newRequestQueue(applicationContext)
            requestQueue.add(stringRequest)
        }

    }
}