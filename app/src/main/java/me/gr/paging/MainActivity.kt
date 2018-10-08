package me.gr.paging

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_main.*
import me.gr.paging.adapter.CheeseListAdapter
import me.gr.paging.adapter.CheeseViewHolder
import me.gr.paging.viewmodel.CheeseViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(CheeseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = CheeseListAdapter()
        cheese_recycler.adapter = adapter
        viewModel.allCheeses.observe(this, Observer(adapter::submitList))

        initListener()
        initSwipeToDelete()
    }

    private fun initListener() {
        add_button.setOnClickListener { addCheese() }

        input_edit.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addCheese()
                true
            } else {
                false
            }
        }

        input_edit.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                addCheese()
                true
            } else {
                false
            }
        }
    }

    private fun initSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(p0: RecyclerView, p1: RecyclerView.ViewHolder): Int {
                return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
            }

            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
                (p0 as CheeseViewHolder).cheese?.let { viewModel.remove(it) }
            }
        }).attachToRecyclerView(cheese_recycler)
    }

    private fun addCheese() = input_edit.text.trim()
            .takeIf { it.isNotEmpty() }
            ?.let {
                viewModel.insert(it)
                input_edit.setText("")
            }
}
