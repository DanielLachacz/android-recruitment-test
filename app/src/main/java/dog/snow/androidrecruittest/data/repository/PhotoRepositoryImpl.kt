package dog.snow.androidrecruittest.data.repository

import androidx.lifecycle.LiveData
import dog.snow.androidrecruittest.data.database.PhotoDao
import dog.snow.androidrecruittest.data.model.RawPhoto
import dog.snow.androidrecruittest.data.service.PhotoService
import io.reactivex.Observable
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val photoService: PhotoService,
private val photoDao: PhotoDao) : PhotoRepository {

    override fun getPhotos(): Observable<List<RawPhoto>> {
       return photoService.getPhotos()
    }

    override suspend fun insertPhotos(photos: List<RawPhoto>) {
        photoDao.insert(photos)
    }

    override fun getPhotosCache(): LiveData<List<RawPhoto>> {
        return photoDao.getPhotos()
    }
}