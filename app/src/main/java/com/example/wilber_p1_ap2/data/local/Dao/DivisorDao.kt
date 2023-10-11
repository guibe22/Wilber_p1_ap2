package com.example.wilber_p1_ap2.data.local.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.wilber_p1_ap2.data.local.entities.Division
import kotlinx.coroutines.flow.Flow

@Dao
interface DivisionDao {
    @Upsert
        suspend fun save(division: Division)

    @Delete
         suspend fun delete(division: Division)


    @Query("SELECT * FROM divisiones")
        fun getAll(): Flow<List<Division>>

}