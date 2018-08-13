package tech.soit.typed.adapter

import android.view.ViewGroup

internal class SwitchTypedBinder<T : Any>(
        internal val binders: List<TypedBinder<T>>,
        val selector: (T) -> Int
) : TypedBinder<T>() {

    companion object {

        internal val DEFAULT_SELECTOR = { _: Any -> 0 }

    }

    override fun attachAdapter(adapter: TypedAdapter) {
        super.attachAdapter(adapter)
        binders.forEach { it.attachAdapter(adapter) }
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        throw RuntimeException("unimplemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, item: T) {
        throw RuntimeException("unimplemented")
    }

}