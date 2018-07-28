package tech.summerly.typed.adapter

import android.view.ViewGroup
import tech.summerly.typed.adapter.annotation.TypeLayoutResource
import kotlin.reflect.full.findAnnotation

/**
 * bind data to view
 *
 */
abstract class TypedBinder<in T : Any> {


    /** internal */
    private lateinit var _adapter: TypedAdapter

    open fun attachAdapter(adapter: TypedAdapter) {
        this._adapter = adapter
    }


    val adapter get() = _adapter

    /**
     * create a ViewHolder for [TypedAdapter]
     *
     * if this binder class annotated with [TypeLayoutResource],
     * return a ViewHolder which holder the view : [TypeLayoutResource.layoutId]
     *
     * or you can override this function to create yourself [ViewHolder]
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @return A new ViewHolder that holds a View for this binder,
     *         it will be use for [onBindViewHolder].
     */
    open fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val annotation = this::class.findAnnotation<TypeLayoutResource>()
        if (annotation != null) {
            return ViewHolder.from(annotation.layoutId, parent)
        }
        throw IllegalStateException("music override this function if you do not use Annotation")
    }

    abstract fun onBindViewHolder(holder: ViewHolder, item: T)


    open fun onBindViewHolder(holder: ViewHolder, item: T, payload: List<Any>) {
        onBindViewHolder(holder, item)
    }


    /**
     * @see TypedAdapter.onViewRecycled
     */
    open fun onViewRecycled(holder: ViewHolder) {

    }

    /**
     * @see TypedAdapter.onViewAttachedToWindow
     */
    open fun onViewAttachedToWindow(holder: ViewHolder) {

    }

    /**
     * @see TypedAdapter.onViewDetachedFromWindow
     */
    open fun onViewDetachedFromWindow(holder: ViewHolder) {

    }
}