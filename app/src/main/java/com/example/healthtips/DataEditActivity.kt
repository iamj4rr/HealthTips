package com.example.healthtips

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.healthtips.`object`.global

class DataEditActivity : AppCompatActivity() {

    private lateinit var myButton : TextView
    private lateinit var etNama : EditText
    private lateinit var etUmur : EditText
    private lateinit var etBerat : EditText
    private lateinit var etTinggi : EditText
    private lateinit var etTingkat_a : EditText
    private lateinit var etJumlah_jam : EditText

    private var urlUpdate : String ="http://iamj4rr.my.id/update_data_user.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data_edit)

        etNama = findViewById(R.id.namaEditText)
        etUmur = findViewById(R.id.umurEditText)
        etBerat = findViewById(R.id.beratEditText)
        etTinggi = findViewById(R.id.tinggiEditText)
        etTingkat_a = findViewById(R.id.tingkatEditText)
        etJumlah_jam = findViewById(R.id.jumlahEditText)

        myButton = findViewById(R.id.save_data)
        myButton.setOnClickListener {
            val nama: String = etNama.text.toString()
            val umur: String = etUmur.text.toString()
            val berat: String = etBerat.text.toString()
            val tinggi: String = etTinggi.text.toString()
            val tingkat_a: String = etTingkat_a.text.toString()
            val jumlah_jam: String = etJumlah_jam.text.toString()

            val sharedPref: SharedPreferences = this@DataEditActivity.getSharedPreferences(global.Pref_Name,MODE_PRIVATE)
            val id = sharedPref.getString("id","").toString()

            val stringRequest: StringRequest = object : StringRequest(
                Method.POST,
                urlUpdate,
                object : Response.Listener<String?> {
                    override fun onResponse(response: String?) {
                        Toast.makeText(applicationContext, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    }
                },
                object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError) {
                        Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): HashMap<String, String>? {
                    val params = HashMap<String, String>()
                    params["post_nama"] = nama
                    params["post_id"] = id
                    params["post_umur"] = umur
                    params["post_berat"] = berat
                    params["post_tinggi"] = tinggi
                    params["post_tingkat_a"] = tingkat_a
                    params["post_jumlah_jam"] = jumlah_jam
                    return params
                }
            }
            val requestQueue = Volley.newRequestQueue(applicationContext)
            requestQueue.add(stringRequest)
        }
    }
}