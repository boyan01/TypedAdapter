package tech.soit.typed.adapter

import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TypedBinderPoolTest {

    companion object {

        private val DEFAULT_MAPPER = { it: Any -> it }

    }

    private val binderPool = TypedBinderPool()

    @Before
    fun register() {
        binderPool.register(Binder0())
        binderPool.register(Binder1())
        binderPool.register(Binder2())
        binderPool.register(Binder3())
        binderPool.register(Binder4())
        binderPool.register(Binder5())
        binderPool.register(Binder6())
        binderPool.register(Binder7())
        binderPool.register(Binder8())
        binderPool.register(Binder9())
    }


    @Test(expected = IllegalStateException::class)
    fun testRegisterDuplicate() {
        binderPool.register(Binder0())
    }

    @Test
    fun testIndex() {

    }


    private inline fun <reified T : Any> TypedBinderPool.register(binder: TypedBinder<T>) {
        @Suppress("UNCHECKED_CAST")
        register(T::class, binder, DEFAULT_MAPPER as (T) -> T)
    }


}