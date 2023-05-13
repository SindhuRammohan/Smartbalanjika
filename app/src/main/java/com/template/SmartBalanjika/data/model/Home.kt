package com.template.SmartBalanjika.data.model

import com.google.gson.annotations.SerializedName


data class Home(
    @SerializedName("categories_list")
    var categoriesList: List<Categories>,
    @SerializedName("home_list")
    var homeList: List<HomeX>,
    @SerializedName("status")
    var status: Boolean, // true
    @SerializedName("total_book")
    var totalBook: Int, // 77
    @SerializedName("unseen_notice")
    var unseenNotice: Int // 139
) {
    data class Categories(
        @SerializedName("id")
        var id: Int, // 27
        @SerializedName("image")
        var image: String, // https://elibrary.smartgov.app/storage/img/categories/1565609176shutterstock_522019972.jpg
        @SerializedName("name")
        var name: String // Novels
    )

    data class HomeX(
        @SerializedName("booklists")
        var booklists: List<Booklists>,
        @SerializedName("id")
        var id: String, // latest_books
        @SerializedName("title")
        var title: String // Recent Releases
    ) {

        data class Booklists(
            @SerializedName("author_name")
            var authorName: String,
            @SerializedName("description")
            var description: String,
            @SerializedName("favourite")
            var favourite: Int, // 0
            @SerializedName("featured_image")
            var featuredImage: String, // https://elibrary.smartgov.app/storage/img/zCZRZXcG7jBZckiVjw032WlUOjNdtP94LJfZolA9.jpeg
            @SerializedName("id")
            var id: Int, // 195
            @SerializedName("name")
            var name: String,
            @SerializedName("publish_by")
            var publishBy: String,
            @SerializedName("published_date")
            var publishedDate: String, // 2020-11-24 05:51:51
            @SerializedName("rating")
            var rating: Any, // null
            @SerializedName("type")
            var type: String, // File
            @SerializedName("url")
            var url: String
        )
    }

}




