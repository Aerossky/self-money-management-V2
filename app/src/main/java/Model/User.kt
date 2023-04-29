package Model

import ModelUang.Uang
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class User(
    var nama: String?,
    var email: String?,
    var password: String? = null,

): Serializable{
    val uanglist: ArrayList<Uang> = ArrayList<Uang>()
}
//: Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString()
//    )
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(nama)
//        parcel.writeString(email)
//        parcel.writeString(password)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<User> {
//        override fun createFromParcel(parcel: Parcel): User {
//            return User(parcel)
//        }
//
//        override fun newArray(size: Int): Array<User?> {
//            return arrayOfNulls(size)
//        }
//    }
//}