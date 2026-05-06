package com.ohuang.kotlin_chinese

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val outputView = findViewById<TextView>(R.id.dslOutput)
        val runButton = findViewById<Button>(R.id.runDslButton)

        outputView.text = 说明文本()

        runButton.setOnClickListener {
            outputView.text = 说明文本()
        }
    }
}
