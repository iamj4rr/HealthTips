package com.example.healthtips

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.healthtips.`object`.global
import org.json.JSONException
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {

    private lateinit var editText1: EditText
    private lateinit var editText2 : EditText
    private lateinit var editText3: EditText
    private lateinit var btn_lgin: Button
    private var urlSignIn : String ="http://iamj4rrmobile.000webhostapp.com/login_service.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          setContentView(R.layout.login)

        editText2 = findViewById(R.id.emailEditText2)
        editText3 = findViewById(R.id.passwordEditText2)
        btn_lgin = findViewById(R.id.button_login)

        btn_lgin.setOnClickListener(View.OnClickListener {
            val password: String = editText3.text.toString()
            val email: String = editText2.text.toString()
            if (!(password.isEmpty() || email.isEmpty())) {
                val stringRequest: StringRequest = object : StringRequest(
                    Method.POST,
                    urlSignIn,
                    object : com.android.volley.Response.Listener<String?> {
                        override fun onResponse(response: String?) {
                            try {
                                val jsonObject = JSONObject(response)
                                val id = jsonObject.getJSONObject("payload").getString("id")
                                val username = jsonObject.getJSONObject("payload").getString("username")
                                val email = jsonObject.getJSONObject("payload").getString("email")
                                val jk = jsonObject.getJSONObject("payload").getString("jk")
                                val nama = jsonObject.getJSONObject("payload").getString("jk")
                                val umur = jsonObject.getJSONObject("payload").getString("jk")
                                val berat = jsonObject.getJSONObject("payload").getString("jk")
                                val tinggi= jsonObject.getJSONObject("payload").getString("jk")
                                val tingkat_a= jsonObject.getJSONObject("payload").getString("jk")
                                val jumlah_jam = jsonObject.getJSONObject("payload").getString("jk")
                                val sharedPref: SharedPreferences = this@LoginActivity.getSharedPreferences(global.Pref_Name,MODE_PRIVATE)
                                val editor = sharedPref.edit()
                                editor.putString("id", id.toString())
                                editor.putString("username", username.toString())
                                editor.putString("email", email.toString())
                                editor.putString("jk", jk.toString())
                                editor.putString("jk", jk.toString())
                                editor.putString("jk", jk.toString())
                                editor.putString("jk", jk.toString())
                                editor.putString("jk", jk.toString())
                                editor.putString("jk", jk.toString())
                                editor.putString("jk", jk.toString())
                                editor.putBoolean("login_session", true)
                                editor.apply()
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }

                            Toast.makeText(applicationContext, "Selamat datang di HealthTips", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, MainActivity::class.java))
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

        val button: Button = findViewById(R.id.signUp)
        button.setOnClickListener {
            val intent: Intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}