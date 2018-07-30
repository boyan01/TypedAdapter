package tech.summerly.typed.example.binder

import tech.summerly.typed.adapter.TypedBinder
import tech.summerly.typed.adapter.ViewHolder
import tech.summerly.typed.adapter.annotation.TypeLayoutResource
import tech.summerly.typed.example.R

object LoadMoreItem

@TypeLayoutResource(R.layout.item_load_more)
class LoadMoreItemBinder : TypedBinder<LoadMoreItem>() {
    override fun onBindViewHolder(holder: ViewHolder, item: LoadMoreItem) {
        //do nothing
    }

}
