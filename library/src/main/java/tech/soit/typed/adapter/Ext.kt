package tech.soit.typed.adapter

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
 * shortcut for [TypedAdapter.withBinders]
 */
inline fun <reified T : Any> TypedAdapter.withBinders(
        vararg binders: TypedBinder<T>,
        noinline binderSwitcher: (T) -> Int): TypedAdapter {
    return withBinders(T::class, binders.toList(), binderSwitcher)
}

/**
 * shortcut for [withBinder]
 */
inline operator fun <reified T : Any> TypedAdapter.plus(binder: TypedBinder<T>): TypedAdapter {
    return withBinder(binder)
}