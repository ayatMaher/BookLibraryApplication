package com.ayat.booklibraryapplication.model

data class Category(
    var id: Int,
    var name: String,
    var author: String,
    var year: Int,
    val self: String,
    val category: String,
    val language: String,
    val number: Int,
    val copies: Int,
    val description: String,
    var img: String = "",
    var isFavorite: Int
) {
    companion object {
        val TABLE_NAME = "category"
        val COL_ID = "id"
        val COL_BOOKNAME = "bookName"
        val COL_AUTHORNAME = "authorName"
        val COL_YEAR = "year"
        val COL_SHELF = "shelf"
        val COL_CATEGORY = "category"
        val COL_LANGUAGE = "language"
        val COL_NUMBER = "number"
        val COL_COPIES = "copies"
        val COL_DESCRIPTION = "description"
        val COL_IMAGE = "image"
        val COL_Fav = "favourite"
        val TABLE_CREATE = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_BOOKNAME TEXT NOT NULL, $COL_AUTHORNAME TEXT,$COL_YEAR INTEGER,$COL_SHELF TEXT" +
                ",$COL_CATEGORY TEXT,$COL_LANGUAGE TEXT,$COL_NUMBER INTEGER,$COL_COPIES INTEGER,$COL_DESCRIPTION TEXT,$COL_IMAGE TEXT,$COL_Fav INTEGER DEFAULT 0)"
    }
}
