package dog.snow.androidrecruittest.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dog.snow.androidrecruittest.data.model.RawUser

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): LiveData<List<RawUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<RawUser>)
}