package com.example.moviecatalouge

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvResponse(
    @SerializedName("results")
    val tv : List<TvShow>,

    ) : Parcelable {
    constructor() : this(mutableListOf())
}