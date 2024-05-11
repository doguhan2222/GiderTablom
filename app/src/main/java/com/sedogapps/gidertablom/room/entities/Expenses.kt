package com.sedogapps.gidertablom.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "expenses",
    foreignKeys = [
        ForeignKey(
            entity = Categories::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Expenses(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Float,
    val date : Float,// Timestamp
    val description: String,
    val category_id: Int // Foreign Key
)

