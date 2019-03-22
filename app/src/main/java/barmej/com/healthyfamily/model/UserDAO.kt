package barmej.com.healthyfamily.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface UserDAO {
    @Query("SELECT * from user_table")
    fun getAllUsers(): List<User>

    @Insert(onConflict = REPLACE)
    fun insertUser(user: User)
}