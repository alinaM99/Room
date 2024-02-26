package com.eg.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table") //Each @Entity class represents a SQLite table
class Word (@PrimaryKey @ColumnInfo (name = "word") val word : String){

}

