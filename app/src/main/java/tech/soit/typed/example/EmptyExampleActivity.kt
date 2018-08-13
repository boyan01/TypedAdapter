package tech.soit.typed.example

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.acitivyt_empty_example.*
import tech.soit.typed.adapter.TypedAdapter
import tech.soit.typed.example.binder.EmptyItem
import tech.soit.typed.example.binder.EmptyItemBinder
import tech.soit.typed.example.binder.StringItemBinder
import tech.soit.typed.example.utils.typedAdapter

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