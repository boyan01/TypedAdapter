package tech.summerly.typed.example

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_swipe_refresh.*
import tech.summerly.typed.adapter.TypedAdapter
import tech.summerly.typed.example.binder.*
import tech.summerly.typed.example.utils.typedAdapter
import kotlin.math.roundToInt

class SwipeRefreshActivity : Activity() {

    /** mutex */
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_refresh)
        val stringItemBinder = StringItemBinder { /**empty on click handle*/ }
        recyclerView.adapter = TypedAdapter()
                .withBinder(EmptyItem::class, EmptyItemBinder())
                .withBinder(LoadMoreItem::class, LoadMoreItemBinder(
                        onLoadMoreShow = {
                            loadMore()
                            Log.i("SwipeRefreshActivity", "on load more show")
                        },
                        onLoadMoreHide = {
                            Log.i("SwipeRefreshActivity", "on load more hide")
                        }))
                .withBinder(Int::class, stringItemBinder) { "data $it" }
                .withBinder(String::class, stringItemBinder)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        swipeRefreshLayout.setOnRefreshListener {
            if (isLoading) {
                return@setOnRefreshListener
            }
            Log.i("SwipeRefresh", "OnRefreshListener")
            swipeRefreshLayout.postDelayed({
                val list = ArrayList<Any>()
                list.addAll((1..20).toList())
                list.add(LoadMoreItem)

                onLoadComplete(list)
            }, 1400)
            isLoading = true
        }

        //simulate to access network resource
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.postDelayed({
            onLoadComplete(listOf(EmptyItem))
        }, 1400)

    }

    private fun onLoadComplete(list: List<Any>) {
        val adapter = recyclerView.typedAdapter
        adapter.submit(list)
        swipeRefreshLayout.isRefreshing = false
        isLoading = false
    }

    /** call back to load more data*/
    private fun loadMore() {
        if (isLoading) {
            return
        }
        swipeRefreshLayout.postDelayed({
            val list = recyclerView.typedAdapter.list.toMutableList()
            // to remove non-data parts, such as LoadMoreItem EmptyItem
            list.removeAll { it !is String && it !is Int }
            //simulate network complete
            val start = (Math.random() * 1000).roundToInt()
            list.addAll((start..(start + 10)).map { "load more : $it" })
            list.add(LoadMoreItem)

            onLoadComplete(list)

        }, 1400)
        isLoading = true

    }

}