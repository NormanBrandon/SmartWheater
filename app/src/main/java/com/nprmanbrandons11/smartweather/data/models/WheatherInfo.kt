package com.nprmanbrandons11.smartweather.data.models

import android.os.Parcel
import android.os.Parcelable

class WheatherInfo(
    val day: Int,
    val wheather: String?,
    val weather_desc: String?,
    val date:String?,
    val max_temp:Double,
    val min_temp: Double,
    val humidity:Int) :Parcelable{

    companion object CREATOR : Parcelable.Creator<WheatherInfo> {
        override fun createFromParcel(parcel: Parcel): WheatherInfo {
            return WheatherInfo(parcel)
        }

        override fun newArray(size: Int): Array<WheatherInfo?> {
            return arrayOfNulls(size)
        }
    }
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeInt(day)
        p0?.writeString(wheather)
        p0?.writeString(weather_desc)
        p0?.writeString(date)
        p0?.writeDouble(max_temp)
        p0?.writeDouble(min_temp)
        p0?.writeInt(humidity)

    }



}


