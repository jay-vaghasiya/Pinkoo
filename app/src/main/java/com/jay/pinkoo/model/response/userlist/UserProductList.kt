package com.jay.pinkoo.model.response.userlist

data class UserProductList(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)