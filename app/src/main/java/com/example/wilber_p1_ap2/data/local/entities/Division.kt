package com.example.wilber_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "divisiones")
class Division (
    @PrimaryKey
    val Id: Int?=null,
    val Dividiendo:Int,
    val Divisor:Int,
    val Cociente:Int,
    val Residuo: Int
    )