package com.template.SmartBalanjika.data.model

import com.google.gson.annotations.SerializedName

class pdf (
    @SerializedName("date")
    var date: String,
    @SerializedName("image")
    var downloadUrl: String, // https://picsum.photos/id/1025/4951/3301
    @SerializedName("bedrooms")
    var height: Int, // 3301
    @SerializedName("link")
    var link: String, // 1025
    @SerializedName("bathrooms")
    var url: String, // https://unsplash.com/photos/U5rMrSI7Pn4
    @SerializedName("size")
    var width: Int // 4951
)