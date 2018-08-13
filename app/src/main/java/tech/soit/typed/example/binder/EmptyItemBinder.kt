package tech.soit.typed.example.binder

import tech.soit.typed.adapter.TypedBinder
import tech.soit.typed.adapter.ViewHolder
import tech.soit.typed.adapter.annotation.TypeLayoutResource
import tech.soit.typed.example.R

object EmptyItem

@TypeLayoutResource(R.layout.item_empty)
class EmptyItemBinder : TypedBinder<EmptyItem>() {


    override fun onBindViewHolder(holder: ViewHolder, item: EmptyItem) {
        //do nothing
    }


}