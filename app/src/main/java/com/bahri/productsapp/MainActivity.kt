package com.bahri.productsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bahri.productsapp.adapter.DataList
import com.bahri.productsapp.databinding.ActivityMainBinding
import com.bahri.productsapp.model.DataProduk
import com.bahri.productsapp.model.ResponseList
import com.bahri.productsapp.network.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    val dataAdapter = DataList(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        rv_item_list.layoutManager = LinearLayoutManager(this)
        rv_item_list.adapter = dataAdapter
        getData()
    }

    private fun getData() {
        ApiService.endNetwork.getList().enqueue(object : Callback<ResponseList>{
            override fun onResponse(call: Call<ResponseList>, response: Response<ResponseList>) {
                if (response.isSuccessful){
                    val responseList: ResponseList? = response.body()
                    onResultData(responseList!!)
                }else{
                    Toast.makeText(this@MainActivity, "Gagal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseList>, t: Throwable) {
                Toast.makeText(this@MainActivity, "$t", Toast.LENGTH_SHORT).show()
                Log.d("Error","$t")
            }

        })
    }

    private fun onResultData(responseList: ResponseList?) {
        val test = responseList?.products
        dataAdapter.setData(test as List<DataProduk>)
    }
}