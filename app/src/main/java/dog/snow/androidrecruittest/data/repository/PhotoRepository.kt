package dog.snow.androidrecruittest.data.repository

import androidx.lifecycle.LiveData
import dog.snow.androidrecruittest.data.model.RawPhoto
import io.reactivex.Observable

interface PhotoRepository {

    fun getPhotos(): Observable<List<RawPhoto>>

    suspend fun insertPhotos(photos: List<RawPhoto>)

    fun getPhotosCache(): LiveData<List<RawPhoto>>
}