package tech.summerly.typed.example.binder

import android.view.View
import tech.summerly.typed.adapter.TypedBinder
import tech.summerly.typed.adapter.ViewHolder
import tech.summerly.typed.adapter.annotation.TypeLayoutResource
import tech.summerly.typed.example.R

object LoadMoreItem

/**
 *
 * a load more item binder for [tech.summerly.typed.adapter.TypedAdapter]
 *
 * @param onLoadMoreShow call back when load more item displaying on the window
 * @param onLoadMoreHide call back when load more item disappear from the window
 *
 * @property View the item view of holder which attached to or detached from window
 */
@TypeLayoutResource(R.layout.item_load_more)
class LoadMoreItemBinder(
        private val onLoadMoreShow: (View) -> Unit = {},
        private val onLoadMoreHide: (View) -> Unit = {}
) : TypedBinder<LoadMoreItem>() {


    override fun onBindViewHolder(holder: ViewHolder, item: LoadMoreItem) {
        //do nothing
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        onLoadMoreShow(holder.itemView)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        onLoadMoreHide(holder.itemView)
    }

}
