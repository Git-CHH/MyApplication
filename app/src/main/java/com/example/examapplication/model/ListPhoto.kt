package com.example.examapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ListPhoto (
    @SerializedName("photo") var photo: List<Photo>? = null
): Parcelable