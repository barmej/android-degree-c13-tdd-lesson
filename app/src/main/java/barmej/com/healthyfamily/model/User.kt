package barmej.com.healthyfamily.model

import java.io.Serializable

data class User(val name: String, var weight: Double, var height: Double, var age: Int, val gender: Gender) : Serializable {
    val heightInM = height / 100.0
}