package com.bignerdranch.android.finalproject

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "table_own")
data class OwnFood(@PrimaryKey val id: UUID = UUID.randomUUID(),
                        var date: Date = Date(),
                        var foodname: String = "",
)