package tech.summerly.typed.example.binder

import tech.summerly.typed.adapter.TypedBinder
import tech.summerly.typed.adapter.ViewHolder
import tech.summerly.typed.adapter.annotation.TypeLayoutResource
import tech.summerly.typed.example.R

object EmptyItem

@TypeLayoutResource(R.layout.item_empty)
class EmptyItemBinder : TypedBinder<EmptyItem>() {


    override fun onBindViewHolder(holder: ViewHolder, item: EmptyItem) {
        //do nothing
    }


}