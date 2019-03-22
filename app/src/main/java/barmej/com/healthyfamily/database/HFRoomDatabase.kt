package barmej.com.healthyfamily.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import barmej.com.healthyfamily.model.User
import barmej.com.healthyfamily.model.UserDAO

@Database(entities = arrayOf(User::class), version = 1)
@TypeConverters(Converters::class)
abstract class HFRoomDatabase: RoomDatabase() {
    abstract fun userDAO(): UserDAO

    companion object {
        @Volatile
        private var INSTANCE: HFRoomDatabase? = null

        fun getDatabase(context: Context): HFRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        HFRoomDatabase::class.java,
                        "HF_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}