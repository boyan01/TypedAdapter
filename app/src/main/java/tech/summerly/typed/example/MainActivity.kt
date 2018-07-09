package tech.summerly.typed.example

import android.app.Activity
import android.os.Bundle
import tech.summerly.typed.adapter.annotation.Binder

@Binder
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
