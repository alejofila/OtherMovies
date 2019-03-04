package com.example.alejofila.themovies.common.uimodel

import android.os.Parcel
import android.os.Parcelable

data class MoviesUiModel(
    val title: String?,
    val overview: String?,
    val releaseDate: String?,
    val imagePath: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeString(imagePath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoviesUiModel> {
        override fun createFromParcel(parcel: Parcel): MoviesUiModel {
            return MoviesUiModel(parcel)
        }

        override fun newArray(size: Int): Array<MoviesUiModel?> {
            return arrayOfNulls(size)
        }
    }
}