package tech.soit.typed.example.binder

import tech.soit.typed.adapter.TypedBinder
import tech.soit.typed.adapter.ViewHolder
import tech.soit.typed.adapter.annotation.TypeLayoutResource
import tech.soit.typed.example.R

object LoadingItem

@TypeLayoutResource(R.layout.item_loading)
class LoadingItemBinder : TypedBinder<LoadingItem>() {

    override fun onBindViewHolder(holder: ViewHolder, item: LoadingItem) {
        //do nothing
    }

}
