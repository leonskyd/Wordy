package com.example.wordy.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataToShow(
    val definitionsToShow: String,
    val synonymsToShow: String
): Parcelable
