package tech.summerly.typed.example

import android.app.Activity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_swipe_refresh.*
import tech.summerly.typed.adapter.TypedAdapter
import tech.summerly.typed.example.binder.*
import tech.summerly.typed.example.utils.typedAdapter

class SwipeRefreshActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_refresh)
        recyclerView.adapter = TypedAdapter()
                .withBinder(EmptyItem::class, EmptyItemBinder())
                .withBinder(LoadMoreItem::class, LoadMoreItemBinder())
                .withBinder(Int::class, StringItemBinder { /**empty on click handle*/ }) { "data $it" }

        swipeRefreshLayout.setOnRefreshListener {
            Log.i("SwipeRefresh", "OnRefreshListener")
            swipeRefreshLayout.postDelayed({
                val adapter = recyclerView.typedAdapter

                val list = ArrayList<Any>()
                list.addAll((1..100).toList())
                list.add(LoadMoreItem)
                adapter.submit(list)
                swipeRefreshLayout.isRefreshing = false
            }, 1400)
        }
    }

}