package tech.summerly.typed.adapter

import kotlin.reflect.KClass

/**
 * see more at [Class.isAssignableFrom]
 */
internal fun KClass<*>.isAssignableFrom(cls: KClass<*>): Boolean {

    return this == cls
            || java.isAssignableFrom(cls.java)
}


/**
 * shortcut for [TypedAdapter.withBinder]
 */
inline fun <reified T : Any> TypedAdapter.withBinder(binder: TypedBinder<T>): TypedAdapter {
    return withBinder(T::class, binder)
}

/**
 * shortcut for [withBinder]
 */
inline operator fun <reified T : Any> TypedAdapter.plus(binder: TypedBinder<T>): TypedAdapter {
    return withBinder(binder)
}