package com.example.healthtips

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.healthtips.`object`.global
import org.json.JSONException
import org.json.JSONObject

class EditActivity : AppCompatActivity () {

    private lateinit var myButton : ImageView
    private lateinit var myButton2 : Button
    private lateinit var myButton3 : TextView
    private lateinit var myButton4 : Button
    private lateinit var etUsername : EditText
    private lateinit var etEmail : EditText
    private lateinit var etJK : EditText
    private var urlUpdate : String ="http://iamj4rr.my.id/update_data_table_user.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit)

        myButton = findViewById(R.id.whiteback)
        myButton.setOnClickListener {
            val intent : Intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        myButton2 = findViewById(R.id.edit_your_data)
        myButton2.setOnClickListener {
            val intent : Intent = Intent(this, DataEditActivity::class.java)
            startActivity(intent)
        }

        val sharedPref: SharedPreferences = this@EditActivity.getSharedPreferences(global.Pref_Name,MODE_PRIVATE)
        val username = sharedPref.getString("username","").toString()
        val email = sharedPref.getString("email","").toString()
        val jk = sharedPref.getString("jk","").toString()

        etUsername = findViewById(R.id.nameEditTextUsername)
        etUsername.setHint(username)

        etEmail =findViewById(R.id.emailEditText3)
        etEmail.setHint(email)

        etJK =findViewById(R.id.jkEditText)
        etJK.setHint((jk))

        etUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    // Jika ada teks, ubah warna teks menjadi merah
                    etUsername.setTextColor(Color.BLACK)
                } else {
                    // Jika tidak ada teks, kembalikan warna teks ke warna asli
                    etUsername.setTextColor(Color.BLACK)
                }
            }
        })
        etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    // Jika ada teks, ubah warna teks menjadi merah
                    etEmail.setTextColor(Color.BLACK)
                } else {
                    // Jika tidak ada teks, kembalikan warna teks ke warna asli
                    etEmail.setTextColor(Color.BLACK)
                }
            }
        })
        etJK.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    // Jika ada teks, ubah warna teks menjadi merah
                    etJK.setTextColor(Color.BLACK)
                } else {
                    // Jika tidak ada teks, kembalikan warna teks ke warna asli
                    etJK.setTextColor(Color.BLACK)
                }
            }
        })

        myButton3 = findViewById(R.id.logout)
        myButton3.setOnClickListener {
            val sharedPref: SharedPreferences = this@EditActivity.getSharedPreferences(global.Pref_Name,MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()
            val intent : Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        myButton4 = findViewById(R.id.save)
        myButton4.setOnClickListener {
            val username: String = etUsername.text.toString()
            val email: String = etEmail.text.toString()
            val jk: String = etJK.text.toString()

            val sharedPref: SharedPreferences = this@EditActivity.getSharedPreferences(global.Pref_Name,MODE_PRIVATE)
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
                        params["post_username"] = username
                        params["post_id"] = id
                        params["post_email"] = email
                        params["post_jk"] = jk
                        return params
                    }
                }
                val requestQueue = Volley.newRequestQueue(
                    applicationContext
                )
                requestQueue.add(stringRequest)

        }
        }
    }
