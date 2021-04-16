package ru.igorsharov.rickandmortyapplication.core.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    companion object {
        private const val AMOUNT_LOAD_USER = 50
        private const val PERCENT_LOAD = 0.2
    }

    var isLoading = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        checkPosition()
    }

    private fun checkPosition() {
        if (isLoading) return

        val lastVisibleItemPos = layoutManager.findLastCompletelyVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount

        if (lastVisibleItemPos != RecyclerView.NO_POSITION
            && (totalItemCount - lastVisibleItemPos) < AMOUNT_LOAD_USER * PERCENT_LOAD
        ) {
            loadNext()
            isLoading = true
        }
    }

    protected abstract fun loadNext()
}