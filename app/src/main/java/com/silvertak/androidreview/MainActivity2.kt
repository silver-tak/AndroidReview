package com.silvertak.androidreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btn.clicks().subscribe { v -> {

        } }
        findViewById<Button>(R.id.btn).setOnClickListener {  };
    }
}