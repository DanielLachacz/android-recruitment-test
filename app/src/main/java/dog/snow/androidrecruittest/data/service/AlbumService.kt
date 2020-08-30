package dog.snow.androidrecruittest.data.service

import dog.snow.androidrecruittest.data.model.RawAlbum
import io.reactivex.Observable
import retrofit2.http.GET

interface AlbumService {

    @GET("albums")
    fun getAlbums():
            Observable<List<RawAlbum>>

}