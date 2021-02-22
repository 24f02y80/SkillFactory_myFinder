package com.yusupovdev.myfinder

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
        val title:String,
        val poster:Int,
        val isInFavorites:Boolean = false,
        val description:String
):Parcelable
