package tech.summerly.typed.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlin.reflect.KClass

open class TypedAdapter constructor(list: List<Any>) : RecyclerView.Adapter<ViewHolder>() {


    constructor() : this(emptyList())

    private var items: List<Any> = list

    private val pool = TypedBinderPool()

    private val mapper = TypedMapper()

    val list: List<Any> get() = items.toList()

    fun <T : Any> withBinder(klass: KClass<T>, binder: TypedBinder<T>): TypedAdapter {
        pool.register(klass, binder)
        binder.attachAdapter(this)
        return this
    }

    /**
     * see [withBinder]
     *
     * @param mapper
     *
     */
    fun <T : Any, R : Any> withBinder(klass: KClass<T>,
                                      binder: TypedBinder<R>,
                                      mapper: (T) -> R): TypedAdapter {
        this.mapper.register(klass, mapper)
        @Suppress("UNCHECKED_CAST")
        return withBinder(klass, binder/*错误的类型转换，但是却没有办法，以后再看看能否优雅一点*/ as TypedBinder<T>)
    }

    fun setList(list: List<*>, notify: Boolean = true) {
        @Suppress("UNCHECKED_CAST")
        this.items = list as List<Any>
        if (notify) {
            notifyDataSetChanged()
        }
    }

    fun submit(list: List<*>) {
        setList(list)
    }

    override fun getItemViewType(position: Int): Int {
        return pool.key(items[position]::class)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binder = pool.getBinder(viewType)
        return binder.onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        @Suppress("UNCHECKED_CAST")
        val binder = pool.getBinder(getItemViewType(position)) as TypedBinder<Any>
        val data = mapper.getMappedObject(items[position])
        binder.onBindViewHolder(holder, data)
    }

    fun getItem(adapterPosition: Int): Any {
        return items[adapterPosition]
    }

}

//type-binder pool to handle type and binder relationship
class TypedBinderPool {

    companion object {
        private const val START = 0
    }


    private val typePool = SparseArrayCompat<KClass<*>>()
    private val binderPool = SparseArrayCompat<TypedBinder<*>>()

    /**
     * when can not directly use typePool.indexOfValue to find the key of class
     * we need to this class' super class , but the process is expensive
     * so we need cache the process'result
     */
    private val cachedSubClassKey = SparseArrayCompat<KClass<*>>()

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

    /**
     * unique key for this type
     */
    fun key(klass: KClass<*>): Int {
        val index = typePool.indexOfValue(klass)
        if (index != -1) {
            return typePool.keyAt(index)
        }

        //if can not find the same class , try to search supper class

        val cachedIndex = cachedSubClassKey.indexOfValue(klass)
        if (cachedIndex != -1) {
            return cachedSubClassKey.keyAt(cachedIndex)
        }

        for (i in 0 until typePool.size()) {
            val c = typePool.valueAt(i)
            if (klass.isAssignableFrom(c)) {
                val key = typePool.keyAt(i)
                cachedSubClassKey.put(key, c)
                return key
            }
        }
        throw IllegalAccessError("class :${klass.simpleName} has not register!!")
    }

    fun getBinder(key: Int): TypedBinder<*> {
        return binderPool[key]
    }

}


internal class TypedMapper {

    private val map = HashMap<KClass<*>, (Any) -> Any>()

    fun <T : Any, R : Any> register(klass: KClass<T>, mapper: (T) -> R) {
        @Suppress("UNCHECKED_CAST")
        map[klass] = mapper as (Any) -> Any
    }

    /**
     * get the object has been mapped
     */
    fun getMappedObject(any: Any): Any {
        //short cuts for empty mapper
        if (map.isEmpty()) {
            return any
        }
        val mapper = map[any::class] ?: return any
        return mapper(any)
    }

}
