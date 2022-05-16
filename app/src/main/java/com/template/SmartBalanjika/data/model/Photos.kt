package com.template.SmartBalanjika.data.model
import com.google.gson.annotations.SerializedName



data class Photos(
    @SerializedName("name")
    var author: String, // Matthew Wiebe
    @SerializedName("image")
    var downloadUrl: String, // https://picsum.photos/id/1025/4951/3301
    @SerializedName("location")
    var location: String, // 3301
    @SerializedName("id")
    var id: String, // 1025
    @SerializedName("phonenumber")
    var phonenumber: String, // https://unsplash.com/photos/U5rMrSI7Pn4
    @SerializedName("size")
    var width: Int // 4951
)