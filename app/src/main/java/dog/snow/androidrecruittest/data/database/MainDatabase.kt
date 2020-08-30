package dog.snow.androidrecruittest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dog.snow.androidrecruittest.data.model.RawAlbum
import dog.snow.androidrecruittest.data.model.RawPhoto
import dog.snow.androidrecruittest.data.model.RawUser

@Database(entities = [RawPhoto::class, RawAlbum::class, RawUser::class],
    version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao
    abstract fun albumDao(): AlbumDao
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var instance: MainDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MainDatabase::class.java, "main.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}