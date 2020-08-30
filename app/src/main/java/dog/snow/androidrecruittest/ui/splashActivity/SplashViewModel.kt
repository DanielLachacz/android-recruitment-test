package dog.snow.androidrecruittest.ui.splashActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.data.model.RawAlbum
import dog.snow.androidrecruittest.data.model.RawPhoto
import dog.snow.androidrecruittest.data.model.RawUser
import dog.snow.androidrecruittest.data.repository.AlbumRepository
import dog.snow.androidrecruittest.data.repository.PhotoRepository
import dog.snow.androidrecruittest.data.repository.UserRepository
import dog.snow.androidrecruittest.di.CoroutineScopeIO
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val photoRepository: PhotoRepository,
private val albumRepository: AlbumRepository,
private val userRepository: UserRepository,
@CoroutineScopeIO private val ioCoroutineScope: CoroutineScope): ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    private lateinit var disposable: Disposable
    private val requests = ArrayList<Observable<*>>()
    var photos = ArrayList<RawPhoto>()
    var albums = ArrayList<RawAlbum>()
    var users = ArrayList<RawUser>()

    init {
        fetchData()
    }

    private fun fetchData() {
        loading.postValue(true)

        requests.add(photoRepository.getPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()))

        requests.add(albumRepository.getAlbums()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()))

        requests.add(userRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()))

        disposable = Observable.zip(requests){

            if (it[0] is List<*>) {                   //photos
                photos = it[0] as ArrayList<RawPhoto>
            }

            if (it[1] is List<*>) {                   //albums
                albums = it[1] as ArrayList<RawAlbum>
            }

            if (it[2] is List<*>) {                 //users
                users = it[2] as ArrayList<RawUser>
            }

        }
            .subscribe({
                ioCoroutineScope.launch {
                    photoRepository.insertPhotos(photos)
                    Timber.e("PHOTOS $photos")
                }
                ioCoroutineScope.launch {
                    albumRepository.insertAlbums(albums)
                    Timber.e("ALBUMS $albums")
                }
                ioCoroutineScope.launch {
                    userRepository.insertUsers(users)
                    Timber.e("USERS $users")
                }
                loading.postValue(false)
                Timber.e("Success")

            }) {
                error.postValue(true)
                Timber.e("Error")
            }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
        ioCoroutineScope.cancel()
    }

}