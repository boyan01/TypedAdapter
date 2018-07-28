package tech.summerly.typed.example

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import tech.summerly.typed.adapter.TypedAdapter
import tech.summerly.typed.adapter.TypedBinder
import tech.summerly.typed.adapter.ViewHolder
import tech.summerly.typed.adapter.annotation.TypeLayoutResource

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = TypedAdapter()
        recyclerView.adapter = adapter
        val stringViewBinder = StringViewBinder()

        adapter.withBinder(String::class, stringViewBinder)
        adapter.withBinder(Int::class, stringViewBinder) { i -> "data : $i" }

        val data = ArrayList<Any>()

        data.addAll(arrayListOf("data1", "data2", "data3"))
        data.addAll((4..20).toList())
        adapter.submit(data)

    }


    @TypeLayoutResource(android.R.layout.simple_list_item_1)
    class StringViewBinder : TypedBinder<String>() {

        override fun onBindViewHolder(holder: ViewHolder, item: String) {
            val textView = holder.itemView.findViewById<TextView>(android.R.id.text1)
            textView.text = item
        }

    }

}