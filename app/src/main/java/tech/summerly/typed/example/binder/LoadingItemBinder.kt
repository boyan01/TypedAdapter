package tech.summerly.typed.example.binder

import tech.summerly.typed.adapter.TypedBinder
import tech.summerly.typed.adapter.ViewHolder
import tech.summerly.typed.adapter.annotation.TypeLayoutResource
import tech.summerly.typed.example.R

object LoadingItem

@TypeLayoutResource(R.layout.item_loading)
class LoadingItemBinder : TypedBinder<LoadingItem>() {

    override fun onBindViewHolder(holder: ViewHolder, item: LoadingItem) {
        //do nothing
    }

}
