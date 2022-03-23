package com.example.test.ViewModel

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.test.Model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    interface UpdateListener {
        fun onUpdate(state: ItemListViewState)
    }

    private var listener: UpdateListener? = null

    init {
        fetchOrders()
    }

    private fun fetchOrderByID(id: Long) {
        NetworkService().api.fetchOrderById(id).enqueue(object : Callback<OrderResponse> {
            override fun onResponse(
                call: Call<OrderResponse>,
                response: Response<OrderResponse>
            ) {
                val itemListViewState =
                    ItemListViewState("Delivery Items", response.body()!!.items.map {
                        ItemRow(it.name)
                    })
                listener?.onUpdate(itemListViewState)
            }

            override fun onFailure(call: Call<OrderResponse?>, t: Throwable) {
            }
        })


    }

    private fun fetchOrders() {
        NetworkService().api.fetchOrders().enqueue(object : Callback<OrdersResponse> {
            override fun onResponse(
                call: Call<OrdersResponse>,
                response: Response<OrdersResponse>
            ) {
                Log.d(ContentValues.TAG, "Total orders" + response.body()!!)
                response.body()!!.orders.forEach {
                    fetchOrderByID(it)
                }
            }

            override fun onFailure(call: Call<OrdersResponse?>, t: Throwable) {
            }
        })


    }

    fun setStateUpdateListener(listener: UpdateListener?) {
        this.listener = listener

    }
}