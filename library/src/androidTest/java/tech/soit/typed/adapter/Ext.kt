package tech.soit.typed.adapter

import tech.soit.typed.adapter.annotation.TypeLayoutResource

class Class0
class Class1
class Class2
class Class3
class Class4
class Class5
class Class6
class Class7
class Class8
class Class9

@TypeLayoutResource(android.R.layout.simple_list_item_1)
open class TestBinder<T : Any> : TypedBinder<T>() {
    override fun onBindViewHolder(holder: ViewHolder, item: T) {

    }
}

class Binder0 : TestBinder<Class0>()
class Binder1 : TestBinder<Class1>()
class Binder2 : TestBinder<Class2>()
class Binder3 : TestBinder<Class3>()
class Binder4 : TestBinder<Class4>()
class Binder5 : TestBinder<Class5>()
class Binder6 : TestBinder<Class6>()
class Binder7 : TestBinder<Class7>()
class Binder8 : TestBinder<Class8>()
class Binder9 : TestBinder<Class9>()
