package dog.snow.androidrecruittest.data.repository

import androidx.lifecycle.LiveData
import dog.snow.androidrecruittest.data.database.UserDao
import dog.snow.androidrecruittest.data.model.RawUser
import dog.snow.androidrecruittest.data.service.UserService
import io.reactivex.Observable
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService,
private val userDao: UserDao) : UserRepository {

    override fun getUsers(): Observable<List<RawUser>> {
       return userService.getUsers()
    }

    override suspend fun insertUsers(users: List<RawUser>) {
        userDao.insert(users)
    }

    override fun getUsersCache(): LiveData<List<RawUser>> {
       return userDao.getUsers()
    }
}