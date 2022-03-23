package com.example.test.Model

import android.content.ContentValues.TAG
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    var mOrders: MutableList<OrdersResponse> = ArrayList()
    private val retrofit: Retrofit = Builder()

        .baseUrl("https://boiling-dusk-12902.herokuapp.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: NetworkApi = retrofit.create(NetworkApi::class.java)


    //first attempt rip
    fun ServiceRequest(){
        var fetchOrders = api.fetchOrders()

        fetchOrders.enqueue(object : Callback<OrdersResponse?> {
            override fun onResponse(
                call: Call<OrdersResponse?>,
                response: Response<OrdersResponse?>

            ) {
                Log.d(TAG,"Total orders" + response.body()!!)
                mOrders.add(response.body()!!)

            }

            override fun onFailure(call: Call<OrdersResponse?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

}