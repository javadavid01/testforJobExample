package com.example.test.Model

import com.google.gson.annotations.SerializedName


class OrderResponse(
    @SerializedName("items") val items: List<DeliveryItem>
)
