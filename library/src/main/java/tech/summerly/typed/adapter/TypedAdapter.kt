package tech.summerly.typed.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlin.reflect.KClass

/**
 * TypedAdapter
 *
 * simple usage:
 *
 * 1. create a [TypedBinder] class to bind DATA with VIEW
 *
 *    @TypeLayoutResource(android.R.layout.test_list_item)
 *
 *    class StringViewBinder : TypedBinder<String>() {
 *
 *        override fun onBindViewHolder(holder: ViewHolder, item: String) {
 *            val textView = holder.itemView.findViewById<TextView>(android.R.id.text1)
 *            textView.text = item
 *        }
 *    }
 *
 * 2. assign adapter to [RecyclerView]
 *    val adapter = TypedAdapter()
 *    recyclerView.adapter = adapter
 *
 * 3. register [TypedBinder] by [withBinder]
 *    val stringViewBinder = StringViewBinder()
 *    adapter.withBinder(String::class, stringViewBinder)
 *
 * 4. submit data by [submit]
 *    adapter.submit(arrayListOf("data1", "data2", "data3"))
 *
 * if you submit a [Int] value , an Exception will be thrown , because you did'nt register
 * a bind for [Int].
 * but you can also use stringViewBinder to handle your [Int] value , by register a bind with a mapper
 *
 *    adapter.withBinder(Int::class, stringViewBinder) { i -> "data : $i" }
 *    adapter.submit((1..100).toList()) // it will handle correctly
 *
 *
 * @author YangBin
 * @date 2018/7/28
 */
open class TypedAdapter : RecyclerView.Adapter<ViewHolder>() {

    companion object {

        private val DEFAULT_MAPPER = { any: Any -> any }

    }

    /**
     * the [RecyclerView] which this adapter attached
     *
     * if adapter do not attached to RecyclerView , a null represent.
     */
    var recyclerView: RecyclerView? = null
        private set(value) {
            field = value
        }

    private var items: List<Any> = emptyList()

    private val pool = TypedBinderPool()

    /** register [binder] with [cls] */
    fun <T : Any> withBinder(cls: KClass<T>, binder: TypedBinder<T>): TypedAdapter {
        @Suppress("UNCHECKED_CAST")
        return withBinder(cls, binder, DEFAULT_MAPPER as (T) -> T)
    }

    /**
     *
     * register a binder with mapper.
     *
     * @param cls the class to register a [TypedBinder]
     * @param binder the binder to bind OBJECT to VIEW ,
     *        it will use mapped value [R] of [T] to bind data to view
     * @param mapper map [T] to [R] before [TypedBinder.onBindViewHolder]
     */
    fun <T : Any, R : Any> withBinder(cls: KClass<T>,
                                      binder: TypedBinder<R>,
                                      mapper: (T) -> R): TypedAdapter {
        pool.register(cls, binder, mapper)
        binder.attachAdapter(this)
        return this
    }

    fun <T : Any> withBinders(cls: KClass<T>,
                              binders: List<TypedBinder<T>>,
                              binderSwitcher: (T) -> Int): TypedAdapter {
        val binder = SwitchTypedBinder(binders, binderSwitcher)
        return withBinder(cls, binder)
    }

    /**
     * 1 Type -> N Binder
     *
     * @param binderSwitcher adapter select the correct Binder by this callback

     */
    fun <T : Any, R : Any> withBinders(cls: KClass<T>,
                                       binders: List<TypedBinder<R>>,
                                       binderSwitcher: (R) -> Int,
                                       mapper: (T) -> R): TypedAdapter {
        val binder = SwitchTypedBinder(binders, binderSwitcher)
        return withBinder(cls, binder, mapper)
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
        val obj = items[position]
        val index = pool.firstBinderForClass(obj::class)
        val selector = pool.getBinderSelector(index)
        // default selector result is 0
        return index + selector(obj)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binder = pool.getBinder(viewType)
        return binder.onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = pool.typeMapper[getItem(position)]
        holder.viewBinder.onBindViewHolder(holder, data)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        val data = pool.typeMapper[items[position]]
        holder.viewBinder.onBindViewHolder(holder, data, payloads)
    }


    /** get the copy of [items] */
    val list: List<Any> get() = items.toList()

    /**
     *
     * get the data of [items] by [adapterPosition]
     *
     * @param adapterPosition see more [ViewHolder.getAdapterPosition]
     */
    fun getItem(adapterPosition: Int): Any {
        return items[adapterPosition]
    }


    override fun onViewAttachedToWindow(holder: ViewHolder) {
        holder.viewBinder.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.viewBinder.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.viewBinder.onViewRecycled(holder)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = null
    }


    /** shortcut to get binder for ViewHolder */
    private val ViewHolder.viewBinder: TypedBinder<Any>
        @Suppress("UNCHECKED_CAST")
        get() = pool.getBinder(itemViewType) as TypedBinder<Any>

}


