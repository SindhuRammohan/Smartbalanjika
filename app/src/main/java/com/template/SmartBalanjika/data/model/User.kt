package com.template.SmartBalanjika.data.model
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("status")
    var status: Boolean, // true
    @SerializedName("user_details")
    var userDetails: UserDetails
){

    data class UserDetails(
        @SerializedName("contact")
        var contact: String, // 9194648464
        @SerializedName("email")
        var email: String, // oleka@gmail.com
        @SerializedName("first_name")
        var firstName: String, // Osan Thanet
        @SerializedName("id")
        var id: Int, // 5019
        @SerializedName("image")
        var image: String, // https://elibrary.smartgov.app/storage/img/user/1596902851name.jpg
        @SerializedName("token")
        var token: String
    )
}
