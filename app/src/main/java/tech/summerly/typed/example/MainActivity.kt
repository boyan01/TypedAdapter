package tech.summerly.typed.example

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import tech.summerly.typed.adapter.TypedAdapter
import tech.summerly.typed.adapter.TypedBinder
import tech.summerly.typed.adapter.ViewHolder
import tech.summerly.typed.adapter.annotation.Binder

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = TypedAdapter()
    }


    @Binder
    class MyItemViewBinder() : TypedBinder<Unit>() {

        override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
            return ViewHolder.from(android.R.layout.simple_list_item_1, parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, item: Unit) {

        }

    }
}