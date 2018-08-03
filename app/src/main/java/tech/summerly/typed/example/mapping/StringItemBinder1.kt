package tech.summerly.typed.example.mapping

import kotlinx.android.synthetic.main.item_string_1.view.*
import tech.summerly.typed.adapter.TypedBinder
import tech.summerly.typed.adapter.ViewHolder
import tech.summerly.typed.adapter.annotation.TypeLayoutResource
import tech.summerly.typed.example.R

@TypeLayoutResource(R.layout.item_string_1)
class StringItemBinder1 : TypedBinder<String>() {

    override fun onBindViewHolder(holder: ViewHolder, item: String) {
        holder.itemView.text.text = item
    }

}