package dog.snow.androidrecruittest.data.repository

import androidx.lifecycle.LiveData
import dog.snow.androidrecruittest.data.model.RawAlbum
import io.reactivex.Observable

interface AlbumRepository {

    fun getAlbums(): Observable<List<RawAlbum>>

    suspend fun insertAlbums(albums: List<RawAlbum>)

    fun getAlbumsCache(): LiveData<List<RawAlbum>>
}