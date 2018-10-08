package me.gr.paging.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import me.gr.paging.data.Cheese
import me.gr.paging.data.CheeseDatabase
import me.gr.paging.util.ioThread

class CheeseViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = CheeseDatabase.get(app).cheeseDao()
    val allCheeses = LivePagedListBuilder(
            dao.allCheesesByName(),
            PagedList.Config.Builder()
                    .setPageSize(15)
                    .setEnablePlaceholders(true)
                    .build())
            .build()

    fun insert(text: CharSequence) = ioThread {
        dao.insert(Cheese(name = text.toString()))
    }

    fun remove(cheese: Cheese) = ioThread {
        dao.delete(cheese)
    }
}