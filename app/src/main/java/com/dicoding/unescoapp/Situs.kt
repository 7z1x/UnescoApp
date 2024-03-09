package com.dicoding.unescoapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Situs(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
