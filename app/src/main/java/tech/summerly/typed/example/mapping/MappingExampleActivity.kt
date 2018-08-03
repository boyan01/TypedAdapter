package tech.summerly.typed.example.mapping

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recyler_view.*
import tech.summerly.typed.adapter.TypedAdapter
import tech.summerly.typed.example.R
import tech.summerly.typed.example.utils.typedAdapter

class MappingExampleActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyler_view)
        recyclerView.adapter = TypedAdapter()
                .withBinder1(
                        String::class,
                        listOf(StringItemBinder1(), StringItemBinder2(), StringItemBinder3()),
                        {
                            when (it.length) {
                                in 0..4 -> 0
                                in 5..10 -> 1
                                else -> 2
                            }
                        },
                        { it })

        recyclerView.typedAdapter.submit(arrayListOf(
                "test",
                "test stringfasfs ",
                "right test",
                "this is veray long test",
                "this is veray long test",
                "this is veray long test",
                "this is veray long test",
                "test",
                "test stringfasfs ",
                "right test",
                "this is veray long test",
                "this is veray long test",
                "this is veray long test",
                "this is veray long test",
                "test",
                "test stringfasfs ",
                "right test",
                "this is veray long test",
                "this is veray long test",
                "this is veray long test",
                "this is veray long test",
                "test",
                "test stringfasfs ",
                "right test",
                "this is veray long test",
                "this is veray long test",
                "this is veray long test",
                "this is veray long test",
                "test",
                "test stringfasfs ",
                "right test",
                "this is veray long test",
                "this is veray long test",
                "this is veray long test",
                "this is veray long test",
                "test",
                "test stringfasfs ",
                "right test",
                "this is veray long test",
                "this is veray long test",
                "this is veray long test",
                "this is veray long test"))
    }
}