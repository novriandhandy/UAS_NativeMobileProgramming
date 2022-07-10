package com.example.moviecatalouge

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("poster_path")
    val poster_path: String?,

    @SerializedName("first_air_date")
    val first_air_date: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("vote_average")
    val vote_average: String?

) : Parcelable {
    constructor() : this("", "", "","","","")
}