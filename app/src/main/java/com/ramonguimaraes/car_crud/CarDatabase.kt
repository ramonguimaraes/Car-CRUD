package com.ramonguimaraes.car_crud

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Car::class], version = 1)
abstract class CarDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao


    companion object {
        private var INSTANCE: CarDatabase? = null

        fun getInstance(context: Context): CarDatabase? {

            if (INSTANCE == null) {
                synchronized(CarDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CarDatabase::class.java,
                        "car.db"
                    ).build()
                }
            }

            return INSTANCE
        }

    }

}