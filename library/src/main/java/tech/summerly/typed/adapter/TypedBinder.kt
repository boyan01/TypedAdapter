package tech.summerly.typed.adapter

import android.view.ViewGroup

/**
 * bind data to view
 *
 */
abstract class TypedBinder<T : Any> {


    private lateinit var _adapter: TypedAdapter

    open fun attachAdapter(adapter: TypedAdapter) {
        this._adapter = adapter
    }


    val adapter get() = _adapter

    abstract fun onCreateViewHolder(parent: ViewGroup): ViewHolder

    abstract fun onBindViewHolder(holder: ViewHolder, item: T)

}