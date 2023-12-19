package com.example.api_trantinet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private val trantinetAdapter: MutableList<Trantinet> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var trantinetsAdapter: TrantinetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.RecyclerView1)
        trantinetsAdapter = TrantinetAdapter(this, trantinetAdapter)
        recyclerView.adapter = trantinetsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        getTrantinetsData()
    }

    // Inside getTrantinetsData function
    private fun getTrantinetsData() {
        val TRANTINETS_URL = "https://mocki.io/v1/107203a3-fa22-4d2d-a1b0-cc4fcdd7a504"
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        val request = JsonArrayRequest(
            Request.Method.GET,
            TRANTINETS_URL,
            null,
            Response.Listener<JSONArray> { response ->
                trantinetAdapter.clear()
                for (i in 0 until response.length()) {
                    try {
                        val jsonObject = response.getJSONObject(i)
                        val trantinet = Trantinet(
                            jsonObject.getInt("id"),
                            jsonObject.getString("name"),
                            jsonObject.getInt("battery_level"),
                            jsonObject.getInt("max_distance"),
                            jsonObject.getString("image")
                        )
                        trantinetAdapter.add(trantinet)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                trantinetsAdapter.notifyDataSetChanged()
            },
            Response.ErrorListener { error ->
                displayCustomToast("Error fetching data: ${error.message}")
            })

        requestQueue.add(request)
    }


    private fun displayCustomToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

