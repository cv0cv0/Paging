package me.gr.paging.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Cheese(@PrimaryKey(autoGenerate = true) val id: Int = 0, val name: String)