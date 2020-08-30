package dog.snow.androidrecruittest.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dog.snow.androidrecruittest.data.model.RawAlbum

@Dao
interface AlbumDao {

    @Query("SELECT * FROM albums")
    fun getAlbums(): LiveData<List<RawAlbum>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<RawAlbum>)
}