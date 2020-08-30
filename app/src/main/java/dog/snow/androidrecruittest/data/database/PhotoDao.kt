package dog.snow.androidrecruittest.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dog.snow.androidrecruittest.data.model.RawPhoto

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos")
    fun getPhotos(): LiveData<List<RawPhoto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<RawPhoto>)
}