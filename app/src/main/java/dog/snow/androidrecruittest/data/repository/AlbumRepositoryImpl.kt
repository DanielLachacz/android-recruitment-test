package dog.snow.androidrecruittest.data.repository

import androidx.lifecycle.LiveData
import dog.snow.androidrecruittest.data.database.AlbumDao
import dog.snow.androidrecruittest.data.model.RawAlbum
import dog.snow.androidrecruittest.data.service.AlbumService
import io.reactivex.Observable
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(private val albumService: AlbumService,
    private val albumDao: AlbumDao) : AlbumRepository {

    override fun getAlbums(): Observable<List<RawAlbum>> {
        return albumService.getAlbums()
    }

    override suspend fun insertAlbums(albums: List<RawAlbum>) {
       albumDao.insert(albums)
    }

    override fun getAlbumsCache(): LiveData<List<RawAlbum>> {
       return albumDao.getAlbums()
    }
}