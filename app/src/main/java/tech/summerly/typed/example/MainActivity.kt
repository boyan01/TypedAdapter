package tech.summerly.typed.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import tech.summerly.typed.adapter.TypedAdapter
import tech.summerly.typed.example.binder.LoadingItem
import tech.summerly.typed.example.binder.LoadingItemBinder
import tech.summerly.typed.example.binder.StringItemBinder

class MainActivity : Activity() {

    private companion object {

        const val NAV_EMPTY = "empty nav"

        const val NAV_SWIPE_REFRESH = "swipe refresh"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TypedAdapter()
        recyclerView.adapter = adapter
                .withBinder(String::class, StringItemBinder(this::onItemClicked))
                .withBinder(LoadingItem::class, LoadingItemBinder())
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        recyclerView.postDelayed({
            // simulate network
            adapter.submit(arrayListOf(NAV_EMPTY, NAV_SWIPE_REFRESH))
        }, 2000)

        adapter.submit(arrayListOf(LoadingItem))
    }

    private fun onItemClicked(str: String) {
        when (str) {
            NAV_EMPTY -> {
                startActivity(Intent(this, EmptyExampleActivity::class.java))
            }
            NAV_SWIPE_REFRESH -> {
                startActivity(Intent(this, SwipeRefreshActivity::class.java))
            }
        }

    }


}