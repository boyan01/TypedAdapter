package tech.soit.typed.example.binder

import android.widget.TextView
import tech.soit.typed.adapter.TypedBinder
import tech.soit.typed.adapter.ViewHolder
import tech.soit.typed.adapter.annotation.TypeLayoutResource


@TypeLayoutResource(android.R.layout.simple_list_item_1)
class StringItemBinder(
        private val onClick: (String) -> Unit
) : TypedBinder<String>() {

    override fun onBindViewHolder(holder: ViewHolder, item: String) {
        val textView = holder.itemView.findViewById<TextView>(android.R.id.text1)
        textView.text = item
        holder.itemView.setOnClickListener { onClick(item) }
    }

}