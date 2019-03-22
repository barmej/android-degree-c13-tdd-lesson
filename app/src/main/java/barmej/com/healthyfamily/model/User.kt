package barmej.com.healthyfamily.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_table")
data class User(@PrimaryKey val name: String, var weight: Double, var height: Double, var age: Int, val gender: Gender) : Serializable {
    @Ignore
    val heightInM = height / 100.0
}