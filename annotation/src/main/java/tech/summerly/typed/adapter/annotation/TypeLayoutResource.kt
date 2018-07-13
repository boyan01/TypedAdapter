package tech.summerly.typed.adapter.annotation

import android.support.annotation.LayoutRes

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class TypeLayoutResource(@LayoutRes val layoutId: Int)