package dog.snow.androidrecruittest.data.service

import dog.snow.androidrecruittest.data.model.RawUser
import io.reactivex.Observable
import retrofit2.http.GET

interface UserService {

    @GET("users")
    fun getUsers():
            Observable<List<RawUser>>

}