package dog.snow.androidrecruittest.data.repository

import androidx.lifecycle.LiveData
import dog.snow.androidrecruittest.data.model.RawUser
import io.reactivex.Observable

interface UserRepository {

    fun getUsers(): Observable<List<RawUser>>

    suspend fun insertUsers(users: List<RawUser>)

    fun getUsersCache(): LiveData<List<RawUser>>
}