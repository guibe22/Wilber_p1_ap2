package com.example.wilber_p1_ap2.data.repository

import com.example.wilber_p1_ap2.data.local.Dao.DivisionDao
import com.example.wilber_p1_ap2.data.local.entities.Division
import javax.inject.Inject

class DivisionRepository @Inject constructor(
    private val DivisionDao:DivisionDao
) {
    suspend fun save(Division: Division) = DivisionDao.save(Division)
    suspend fun delete(Division: Division)= DivisionDao.delete(Division)

    fun getAll()= DivisionDao.getAll()
}