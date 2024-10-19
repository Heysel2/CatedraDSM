package com.example.myplanner

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Clase que gestiona la base de datos
class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Constantes para la base de datos y la tabla
    companion object {
        private const val DATABASE_NAME = "activities.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "Activities"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_DATE = "date"
        const val COLUMN_TIME = "time"
    }

    // Método que se llama al crear la base de datos
    override fun onCreate(db: SQLiteDatabase) {
        // Se crea la tabla "Activities"
        val CREATE_TABLE = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_TITLE TEXT, "
                + "$COLUMN_DESCRIPTION TEXT, "
                + "$COLUMN_DATE TEXT, "
                + "$COLUMN_TIME TEXT)")
        db.execSQL(CREATE_TABLE)
    }

    // Método que se llama al actualizar la versión de la base de datos
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Método para agregar una actividad
    fun addActivity(title: String, description: String, date: String, time: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_DESCRIPTION, description)
            put(COLUMN_DATE, date)
            put(COLUMN_TIME, time)
        }
        val success = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return success
    }

    // Método para obtener todas las actividades
    fun getAllActivities(): List<Activity> {
        val activityList = ArrayList<Activity>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val activity = Activity(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                    description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                    date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)),
                    time = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME))
                )
                activityList.add(activity)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return activityList
    }

    // Método para eliminar una actividad por ID
    fun deleteActivity(id: Int): Int {
        val db = this.writableDatabase
        val success = db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
        return success
    }

}
