package com.example.recyclerviewkotlinexample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewkotlinexample.adapters.UserAdapter
import com.example.recyclerviewkotlinexample.data.Users
import com.example.recyclerviewkotlinexample.databinding.ActivityFirstBinding
import com.example.recyclerviewkotlinexample.singleton.RetrofitClient
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response

class FirstActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirstBinding
    lateinit var view: View
    lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getAPI();
    }

    private fun getAPI() {
        RetrofitClient.api.getUserList().enqueue(object :
            retrofit2.Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    val userList = response.body()
                    Log.d("API_SUCCESS", userList.toString())
                    binding.rvUser.layoutManager = LinearLayoutManager(this@FirstActivity)
                    adapter = UserAdapter(userList!!)
                    binding.rvUser.adapter = adapter
                    Log.d("Testing", "response successful")
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Log.e("API_ERROR", t.message ?: "Unknown error")
            }
        })
    }
}