package dog.snow.androidrecruittest.data.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users")
data class RawUser(
    @PrimaryKey
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    @Embedded(prefix = "address_")
    val address: RawAddress,
    val phone: String,
    val website: String,
    @Embedded(prefix = "company_")
    val company: RawCompany
) : Parcelable {
    @Parcelize
    data class RawAddress(
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String,
        @Embedded(prefix = "geo_")
        val geo: RawGeo
    ) : Parcelable {
        @Parcelize
        data class RawGeo(val lat: String, val lng: String) : Parcelable
    }

    @Parcelize
    data class RawCompany(
        val name: String,
        val catchPhrase: String,
        val bs: String
    ) : Parcelable
}