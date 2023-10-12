package com.example.wilber_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "divisiones")
class Division (
    @PrimaryKey
    val DividirId: Int?=null,
    val Nombre:String,
    val Dividiendo:String,
    val Divisor:String,
    val Cociente:String,
    val Residuo: String
    )