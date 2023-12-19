package com.example.healthtips

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class SignUpActivity : AppCompatActivity() {

    private lateinit var editText1: EditText
    private lateinit var editText2 : EditText
    private lateinit var editText3: EditText
    private lateinit var btn_signin : Button
    private var urlSignIn : String ="http://iamj4rr.my.id/sign_in.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)


        val myButton2: Button = findViewById(R.id.log_in)
        myButton2.setOnClickListener {
            val intent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        editText1 = findViewById(R.id.nameEditText)
        editText2 = findViewById(R.id.emailEditText1)
        editText3 = findViewById(R.id.passwordEditText1)
        btn_signin = findViewById(R.id.sign_in)

        btn_signin.setOnClickListener(View.OnClickListener {
            val username: String = editText1.text.toString()
            val password: String = editText3.text.toString()
            val email: String = editText2.text.toString()
            if (!(username.isEmpty() || password.isEmpty() || email.isEmpty())) {
                val stringRequest: StringRequest = object : StringRequest(
                    Method.POST,
                    urlSignIn,
                    object : Listener<String?> {
                        override fun onResponse(response: String?) {
                            Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, LoginActivity::class.java))
                        }
                    },
                    object : ErrorListener {
                        override fun onErrorResponse(error: VolleyError) {
                            Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }) {
                    @Throws(AuthFailureError::class)
                    override fun getParams(): HashMap<String, String>? {
                        val params = HashMap<String, String>()
                        params["post_username"] = username
                        params["post_password"] = password
                        params["post_email"] = email
                        return params
                    }
                }
                val requestQueue = Volley.newRequestQueue(
                    applicationContext
                )
                requestQueue.add(stringRequest)
            } else {
                Toast.makeText(applicationContext, "Ada Data Yang Masih Kosong", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    // Jika ada teks, ubah warna teks menjadi merah
                    editText1.setTextColor(Color.BLACK)
                } else {
                    // Jika tidak ada teks, kembalikan warna teks ke warna asli
                    editText1.setTextColor(Color.WHITE)
                }
            }
        })
        editText2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    // Jika ada teks, ubah warna teks menjadi merah
                    editText2.setTextColor(Color.BLACK)
                } else {
                    // Jika tidak ada teks, kembalikan warna teks ke warna asli
                    editText2.setTextColor(Color.WHITE)
                }
            }
        })
        editText3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    // Jika ada teks, ubah warna teks menjadi merah
                    editText3.setTextColor(Color.BLACK)
                } else {
                    // Jika tidak ada teks, kembalikan warna teks ke warna asli
                    editText3.setTextColor(Color.WHITE)
                }
            }
        })

    }
}