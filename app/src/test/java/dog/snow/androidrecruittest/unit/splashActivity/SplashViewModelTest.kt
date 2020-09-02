package dog.snow.androidrecruittest.unit.splashActivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dog.snow.androidrecruittest.data.model.RawAlbum
import dog.snow.androidrecruittest.data.model.RawPhoto
import dog.snow.androidrecruittest.data.model.RawUser
import dog.snow.androidrecruittest.data.repository.AlbumRepository
import dog.snow.androidrecruittest.data.repository.PhotoRepository
import dog.snow.androidrecruittest.data.repository.UserRepository
import dog.snow.androidrecruittest.ui.splashActivity.SplashViewModel
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var photoRepository: PhotoRepository

    @Mock
    lateinit var albumRepository: AlbumRepository

    @Mock
    lateinit var userRepository: UserRepository

    lateinit var splashViewModel: SplashViewModel
    private lateinit var photosObs: Observable<List<RawPhoto>>
    private lateinit var albumsObs: Observable<List<RawAlbum>>
    private lateinit var usersObs: Observable<List<RawUser>>
    private val requests = ArrayList<Observable<*>>()
    private  var loading: Boolean = true
    private  var error: Boolean = false

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun fetchData_success() {
        var photo1 = RawPhoto(1, 1, "One", "One", "One")
        var album1 = RawAlbum(1, 1, "One")
        var geo = RawUser.RawAddress.RawGeo("One", "One")
        var address = RawUser.RawAddress("One", "One", "One", "One", geo)
        var company = RawUser.RawCompany("One", "One", "One")
        var user1 = RawUser(1, "One", "One", "One", address, "One", "One", company)
        var photoList = ArrayList<RawPhoto>()
        photoList.add(photo1)
        var albumList = ArrayList<RawAlbum>()
        albumList.add(album1)
        var userList = ArrayList<RawUser>()
        userList.add(user1)

        requests.add(albumsObs)
        requests.add(usersObs)
        requests.add(photosObs)

        if (albumRepository != null && photoRepository != null && userRepository != null) {
            Mockito. `when` (albumRepository.getAlbums())
            Mockito. `when` (photoRepository.getPhotos())
            Mockito. `when` (userRepository.getUsers())
        }

        splashViewModel.fetchData()
        assertEquals(1, splashViewModel.photos.size)
        assertEquals(1, splashViewModel.albums.size)
        assertEquals(1, splashViewModel.users.size)
        assertEquals(loading, splashViewModel.loading.value)
    }


}