package tech.summerly.typed.example

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import tech.summerly.typed.adapter.TypedAdapter
import tech.summerly.typed.adapter.TypedBinder
import tech.summerly.typed.adapter.ViewHolder
import tech.summerly.typed.adapter.annotation.Binder
import tech.summerly.typed.adapter.annotation.TypeLayoutResource

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = TypedAdapter()
        recyclerView.adapter = adapter
        adapter.withBinder(Unit::class, MyItemViewBinder())
        adapter.submit((0..100).map { Unit })
    }


    @Binder
    @TypeLayoutResource(android.R.layout.simple_list_item_1)
    class MyItemViewBinder : TypedBinder<Unit>() {

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, item: Unit) {
            val text = holder.itemView.findViewById<TextView>(android.R.id.text1)
            text.text = "test data : ${holder.adapterPosition}"
        }

    }
}