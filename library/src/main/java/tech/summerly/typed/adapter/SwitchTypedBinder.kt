package tech.summerly.typed.adapter

import android.view.ViewGroup

class SwitchTypedBinder<T : Any>(
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
        throw RuntimeException("unImplement")
    }

    override fun onBindViewHolder(holder: ViewHolder, item: T) {
        throw RuntimeException("unImplement")
    }

}