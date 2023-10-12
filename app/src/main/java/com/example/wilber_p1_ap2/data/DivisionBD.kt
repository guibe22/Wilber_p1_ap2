package com.example.wilber_p1_ap2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wilber_p1_ap2.data.local.Dao.DivisionDao
import com.example.wilber_p1_ap2.data.local.entities.Division

@Database(
    entities = [Division::class ],
    version = 2,
    exportSchema = false
)
abstract class DivisionBD: RoomDatabase() {
    abstract fun DivisionDao(): DivisionDao
}