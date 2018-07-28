package tech.summerly.typed.adapter

import android.support.v4.util.SparseArrayCompat
import kotlin.reflect.KClass

/**
 * type-binder pool to handle type and binder relationship
 */
internal class TypedBinderPool {

    companion object {

        /** the start point to generate ITEM_VIEW_TYPE */
        private const val START = 1001

    }


    private val typePool = SparseArrayCompat<KClass<*>>()
    private val binderPool = SparseArrayCompat<TypedBinder<*>>()

    val typeMapper = TypedMapper()

    private var typeAutoIncrement = START

    @Synchronized
    fun <T : Any> register(klass: KClass<T>, binder: TypedBinder<T>) {
        val index = typePool.indexOfValue(klass)
        if (index > 0) {
            return
        }
        typePool.put(typeAutoIncrement, klass)
        binderPool.put(typeAutoIncrement, binder)
        typeAutoIncrement++
    }

    @Synchronized
    fun <T : Any, R : Any> register(klass: KClass<T>, binder: TypedBinder<R>, mapper: ((T) -> R)) {
        val index = typePool.indexOfValue(klass)
        if (index > 0) { // already registered
            return
        }
        typePool.put(typeAutoIncrement, klass)
        binderPool.put(typeAutoIncrement, binder)
        typeMapper.register(klass, mapper)
        typeAutoIncrement++
    }

    /**
     * unique key for this type
     */
    fun key(klass: KClass<*>): Int {
        val index = typePool.indexOfValue(klass)
        if (index != -1) {
            return typePool.keyAt(index)
        }

        //if can not find the same class , try to search supper class
        for (i in 0 until typePool.size()) {
            val c = typePool.valueAt(i)
            if (klass.isAssignableFrom(c)) {
                return typePool.keyAt(i)
            }
        }
        throw IllegalAccessError("class :${klass.simpleName} has not register!!")
    }

    fun getBinder(key: Int): TypedBinder<*> {
        return binderPool[key]
    }


    internal class TypedMapper {

        /**
         * key : the class of TYPE
         * value: the mapper function to map TYPE (key) to another type
         */
        private val map = HashMap<KClass<*>, (Any) -> Any>()

        /** register mapper for [cls] */
        fun <T : Any, R : Any> register(cls: KClass<T>, mapper: (T) -> R) {
            @Suppress("UNCHECKED_CAST")
            map[cls] = mapper as (Any) -> Any
        }

        /**
         * get the object has been mapped
         * if param [any] has not [register] mapper function yet, it will return it self.
         */
        operator fun get(any: Any): Any {
            //short cuts for empty mapper
            if (map.isEmpty()) {
                return any
            }
            val mapper = map[any::class] ?: return any
            return mapper(any)
        }

    }

}