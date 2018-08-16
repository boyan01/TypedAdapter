package tech.soit.typed.adapter.annotation

import androidx.annotation.LayoutRes

/**
 * An annotation used to indicate what layout id an TypedBinder support
 *
 *
 * @param layoutId the layout id used to inflated
 *
 */
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class TypeLayoutResource(@LayoutRes val layoutId: Int)