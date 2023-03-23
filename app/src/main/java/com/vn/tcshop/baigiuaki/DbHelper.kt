package com.vn.tcshop.baigiuaki

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE)
        val sql = "insert into " + TB_NAME + " values (NULL,'congnt.21it@vku.udn.vn','cong','0987')"
        sqLiteDatabase.execSQL(sql)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("drop table if exists " + TB_NAME)
        onCreate(sqLiteDatabase)
    }

    fun addSv(rg: Register) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_EMAIL, rg.email)
        values.put(COL_NAME, rg.ten)
        values.put(COL_PASS, rg.matkhau)
        db.insert(TB_NAME, null, values)
        db.close()
    }

    companion object {
        private const val DB_NAME = "dbdangky.db"
        private const val DB_VERSION = 1
        private const val TB_NAME = "dangky"
        private const val COL_ID = "id"
        private const val COL_EMAIL = "email"
        private const val COL_NAME = "ten"
        private const val COL_PASS = "matkhau"
        private const val CREATE_TABLE = ("CREATE TABLE " + TB_NAME + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_EMAIL + " TEXT," + COL_NAME + " TEXT," + COL_PASS + " TEXT"
                + ")")
    }
}