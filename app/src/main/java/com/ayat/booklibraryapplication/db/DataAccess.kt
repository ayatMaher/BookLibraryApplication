package com.ayat.booklibraryapplication.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ayat.booklibraryapplication.model.Category

class DataAccess(var context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    var db: SQLiteDatabase = writableDatabase


    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(Category.TABLE_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS ${Category.TABLE_NAME}")
        onCreate(p0)
    }

    fun insertBook(
        bookName: String,
        authorName: String,
        year: Int,
        shelf: String,
        category: String,
        language: String,
        number: Int,
        copies: Int,
        description: String,
        image: String
    ): Boolean {
        val cv = ContentValues()
        cv.put(Category.COL_BOOKNAME, bookName)
        cv.put(Category.COL_AUTHORNAME, authorName)
        cv.put(Category.COL_YEAR, year)
        cv.put(Category.COL_SHELF, shelf)
        cv.put(Category.COL_CATEGORY, category)
        cv.put(Category.COL_LANGUAGE, language)
        cv.put(Category.COL_NUMBER, number)
        cv.put(Category.COL_COPIES, copies)
        cv.put(Category.COL_DESCRIPTION, description)
        cv.put(Category.COL_IMAGE, image)
        return db.insert(Category.TABLE_NAME, null, cv) > 0
    }

    fun updateBook(
        id: Int,
        bookName: String,
        authorName: String,
        year: Int,
        shelf: String,
        category: String,
        language: String,
        number: Int,
        copies: Int,
        description: String,
        image: String
    ): Boolean {
        val cv = ContentValues()
        cv.put(Category.COL_BOOKNAME, bookName)
        cv.put(Category.COL_AUTHORNAME, authorName)
        cv.put(Category.COL_YEAR, year)
        cv.put(Category.COL_SHELF, shelf)
        cv.put(Category.COL_CATEGORY, category)
        cv.put(Category.COL_LANGUAGE, language)
        cv.put(Category.COL_NUMBER, number)
        cv.put(Category.COL_COPIES, copies)
        cv.put(Category.COL_DESCRIPTION, description)
        cv.put(Category.COL_IMAGE, image)
        return db.update(Category.TABLE_NAME, cv, "${Category.COL_ID} = $id", null) > 0
    }

    fun favoriteBook(
        id: Int, favorite: Int
    ): Boolean {
        val cv = ContentValues()
        cv.put(Category.COL_Fav, favorite)
        return db.update(Category.TABLE_NAME, cv, "${Category.COL_ID} = $id", null) > 0
    }

    fun deleteBook(id: Int): Boolean {
        return db.delete(Category.TABLE_NAME, "${Category.COL_ID} = $id", null) > 0
    }

    fun getAllBook(): ArrayList<Category> {
        val arr = ArrayList<Category>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${Category.TABLE_NAME}", null)
        while (cursor.moveToNext()) {
            val book = Category(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getInt(7),
                cursor.getInt(8),
                cursor.getString(9),
                cursor.getString(10),
                cursor.getInt(11)
            )
            arr.add(book)
        }
        cursor.close()
        return arr
    }

    fun getFavouriteBook(): ArrayList<Category> {
        val arr = ArrayList<Category>()
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${Category.TABLE_NAME}  where  + ${Category.COL_Fav} = 1",
            null
        )
        while (cursor.moveToNext()) {
            val book = Category(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getInt(7),
                cursor.getInt(8),
                cursor.getString(9),
                cursor.getString(10),
                cursor.getInt(11)
            )
            arr.add(book)
        }
        cursor.close()
        return arr
    }

    fun searchBook(name: String): ArrayList<Category> {
        val arr = ArrayList<Category>()
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${Category.TABLE_NAME} where ${Category.COL_BOOKNAME} LIKE '$name%'",
            null
        )
        while (cursor.moveToNext()) {
            val book = Category(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getInt(7),
                cursor.getInt(8),
                cursor.getString(9),
                cursor.getString(10),
                cursor.getInt(11)
            )
            arr.add(book)
        }
        cursor.close()
        return arr
    }

    companion object {
        val DATABASE_VERSION = 3
        val DATABASE_NAME = "LibraryDB"
    }
}