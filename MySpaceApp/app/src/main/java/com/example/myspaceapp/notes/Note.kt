package com.example.myspaceapp.notes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(
    val id: Int,
    val title: String,
    val category: String,
    val description: String
): Parcelable
