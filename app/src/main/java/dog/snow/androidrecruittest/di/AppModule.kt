package dog.snow.androidrecruittest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dog.snow.androidrecruittest.data.database.AlbumDao
import dog.snow.androidrecruittest.data.database.MainDatabase
import dog.snow.androidrecruittest.data.database.PhotoDao
import dog.snow.androidrecruittest.data.database.UserDao
import dog.snow.androidrecruittest.data.repository.*
import dog.snow.androidrecruittest.data.service.AlbumService
import dog.snow.androidrecruittest.data.service.PhotoService
import dog.snow.androidrecruittest.data.service.UserService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.jetbrains.annotations.Nullable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun photoRepository(photoService: PhotoService, photoDao: PhotoDao): PhotoRepository {
        return PhotoRepositoryImpl(photoService, photoDao)
    }

    @Singleton
    @Provides
    fun albumRepository(albumService: AlbumService, albumDao: AlbumDao): AlbumRepository {
        return AlbumRepositoryImpl(albumService, albumDao)
    }

    @Singleton
    @Provides
    fun userRepository(userService: UserService, userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userService, userDao)
    }

    @Singleton
    @Provides
    fun provideRetrofitServicePhoto(@Nullable retrofit: Retrofit): PhotoService {
        return retrofit.create(PhotoService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitServiceAlbum(@Nullable retrofit: Retrofit): AlbumService {
        return retrofit.create(AlbumService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitServiceUser(@Nullable retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, gsonConverterFactory: GsonConverterFactory
                        , okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOKHttpClient():OkHttpClient{
        return  OkHttpClient.Builder()
            .readTimeout(5200, TimeUnit.SECONDS)
            .connectTimeout(5200, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRXCallAdapter(): RxJava2CallAdapterFactory {
        return  RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory {
        return  GsonConverterFactory.create()
    }

    @CoroutineScopeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    @Singleton
    @Provides
    fun provideDatabase(app: Context) = MainDatabase.getInstance(app)

    @Singleton
    @Provides
    fun providePhotoDao(db: MainDatabase) = db.photoDao()

    @Singleton
    @Provides
    fun provideAlbumDao(db: MainDatabase) = db.albumDao()

    @Singleton
    @Provides
    fun provideUserDao(db: MainDatabase) = db.userDao()
}