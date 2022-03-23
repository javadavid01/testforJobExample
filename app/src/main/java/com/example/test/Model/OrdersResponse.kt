package com.example.test.Model

import com.google.gson.annotations.SerializedName


class OrdersResponse(
    @SerializedName("orders") val orders: List<Long>
)