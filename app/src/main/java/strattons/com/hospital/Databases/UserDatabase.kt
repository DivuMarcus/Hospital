package strattons.com.hospital.Databases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabase : SQLiteOpenHelper {

    object staticated {
        var DB_VERSION = 1
        val DB_NAME = "UserDatabase"
        val TABLE_NAME = "UserTable"
        val USER_ID = "USERNAME"
        val EMAIL_ID = "EMAILID"
        val MOBILENO = "MOBILENO"
        val ADDRESS = "ADDRESS"
        val PASSWORD = "PASSWORD"
    }

    override fun onCreate(sqlitedatabase: SQLiteDatabase?) {
        sqlitedatabase?.execSQL("CREATE TABLE " + staticated.TABLE_NAME + "( " + staticated.USER_ID + "STRING PRIMARY KEY," +
                staticated.EMAIL_ID + " STRING" + staticated.MOBILENO + " INTEGER" + staticated.ADDRESS + "STRING" + staticated.PASSWORD +
                "String");
    }

    override fun onUpgrade(sqlitedatabase: SQLiteDatabase?, p1: Int, p2: Int) {
        sqlitedatabase?.execSQL("DROP TABLE IF EXISTS staticated.TABLE_NAME")
    }

    fun insertUser(name: String?, email: String?, mobileno: Int?, address: String?, password: String?) {
        val db = this.writableDatabase
        val contentValues = ContentValues()


    }

    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : super(context, name, factory, version)

}