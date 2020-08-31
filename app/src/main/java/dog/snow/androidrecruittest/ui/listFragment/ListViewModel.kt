package dog.snow.androidrecruittest.ui.listFragment

import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.data.repository.AlbumRepository
import dog.snow.androidrecruittest.data.repository.PhotoRepository
import dog.snow.androidrecruittest.data.repository.UserRepository
import javax.inject.Inject

class ListViewModel @Inject constructor(private val photoRepository: PhotoRepository,
private val albumRepository: AlbumRepository,
private val userRepository: UserRepository): ViewModel() {

    fun getPhotosCache() = photoRepository.getPhotosCache()

    fun getAlbumsCache() = albumRepository.getAlbumsCache()

    fun getUsersCache() = userRepository.getUsersCache()
}