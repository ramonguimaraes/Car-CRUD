package com.ramonguimaraes.car_crud

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Car(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var name: String
)