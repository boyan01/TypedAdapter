package tech.soit.typed.adapter

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *
 * A ViewHolder describes an item view and metadata about
 * its place within the RecyclerView.
 *
 * ViewHolder be used in [TypedBinder].
 *
 * @param itemView the view of ViewHolder described,
 *                 your can use [itemView] to get it
 *
 */
open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    companion object {

        /**
         * shortcut function to build a ViewHolder from [TypedBinder.onCreateViewHolder]
         *
         * @param layoutId the layout id to be inflated
         * @param parent The ViewGroup into which the new View will be added
         */
        fun from(@LayoutRes layoutId: Int, parent: ViewGroup): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ViewHolder(inflater.inflate(layoutId, parent, false))
        }
    }

}