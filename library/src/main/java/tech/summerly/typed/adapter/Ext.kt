@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package tech.summerly.typed.adapter

import kotlin.reflect.KClass
import java.lang.Object as JObject

/**
 * see more at [Class.isAssignableFrom]
 */
fun KClass<*>.isAssignableFrom(cls: KClass<*>): Boolean {

    return this == cls
            || java.isAssignableFrom(cls.java)
}