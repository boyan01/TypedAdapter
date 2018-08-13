package tech.soit.typed.example.mapping

import kotlinx.android.synthetic.main.item_string_1.view.*
import tech.soit.typed.adapter.TypedBinder
import tech.soit.typed.adapter.ViewHolder
import tech.soit.typed.adapter.annotation.TypeLayoutResource
import tech.soit.typed.example.R

@TypeLayoutResource(R.layout.item_string_1)
class StringItemBinder1 : TypedBinder<String>() {

    override fun onBindViewHolder(holder: ViewHolder, item: String) {
        holder.itemView.text.text = item
    }

}