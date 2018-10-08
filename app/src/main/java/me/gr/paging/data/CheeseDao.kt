package me.gr.paging.data

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CheeseDao {

    @Query("select * from cheese order by name collate nocase asc")
    fun allCheesesByName(): DataSource.Factory<Int, Cheese>

    @Insert
    fun insert(cheese: List<Cheese>)

    @Insert
    fun insert(cheese: Cheese)

    @Delete
    fun delete(cheese: Cheese)
}