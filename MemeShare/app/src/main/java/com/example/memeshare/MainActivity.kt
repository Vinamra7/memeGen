package com.example.memeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        load()

    }
    var xyz=""
    private fun load(){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url22 = "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url22, null,
            { response ->
                val url2 = response.getString("url").toString()
                Glide.with(this).load(url2).into(findViewById(R.id.imageView))
                xyz=url2
                val tt = Toast.makeText(this,response.getString("title"),Toast.LENGTH_LONG)
                tt.show()
            },
            {
                val showT = Toast.makeText(this,"error",Toast.LENGTH_SHORT)
                showT.show()
            }
        )
        queue.add(jsonObjectRequest)

    }
    fun shareCall(view: View) {
    val sharer = Intent()
        sharer.action=Intent.ACTION_SEND
        sharer.type="text/plain"
        sharer.putExtra(Intent.EXTRA_TEXT,xyz)
        startActivity(Intent.createChooser(sharer,"Share via"))
    }
    fun nextCall(view: View){
    load()
    }
}