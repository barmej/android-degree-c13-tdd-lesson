package barmej.com.healthyfamily.database

import androidx.room.TypeConverter
import barmej.com.healthyfamily.model.Gender

class Converters {

    @TypeConverter
    fun fromInteger(value: Int?): Gender? {
        return Gender.fromIndex(value)
    }

    @TypeConverter
    fun genderToInteger(gender: Gender?): Int? {
        return Gender.toIndex(gender)
    }
}