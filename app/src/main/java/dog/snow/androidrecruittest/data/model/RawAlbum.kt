package dog.snow.androidrecruittest.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "albums")
data class RawAlbum(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String
) : Parcelable
