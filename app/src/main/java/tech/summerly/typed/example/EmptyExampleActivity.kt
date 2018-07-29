package tech.summerly.typed.example

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.acitivyt_empty_example.*
import tech.summerly.typed.adapter.TypedAdapter
import tech.summerly.typed.example.binder.EmptyItem
import tech.summerly.typed.example.binder.EmptyItemBinder
import tech.summerly.typed.example.binder.StringItemBinder
import tech.summerly.typed.example.utils.typedAdapter

class EmptyExampleActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivyt_empty_example)
        recyclerView.adapter = TypedAdapter()
                .withBinder(String::class, StringItemBinder { /* empty click handler */ })
                .withBinder(EmptyItem::class, EmptyItemBinder())
        recyclerView.typedAdapter.submit(arrayListOf(EmptyItem))
    }


}