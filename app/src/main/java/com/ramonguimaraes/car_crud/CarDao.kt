package com.ramonguimaraes.car_crud

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarDao {

    @Insert
    fun insert(car: Car)

    @Query("SELECT MAX(id) FROM Car")
    fun findLastCarId(): Long

    @Query("SELECT * FROM Car ORDER BY name ASC")
    fun getAllCar(): List<Car>


    @Query("DELETE from Car")
    fun deleteAll()

}