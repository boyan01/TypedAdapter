package tech.summerly.typed.adapter

import kotlin.reflect.KClass

/**
 * see more at [Class.isAssignableFrom]
 */
fun KClass<*>.isAssignableFrom(cls: KClass<*>): Boolean {

    return this == cls
            || java.isAssignableFrom(cls.java)
}