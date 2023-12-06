package com.example.tictactoek

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WinActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)

        val tvWinner: TextView = findViewById(R.id.textView2)
        val btn: Button = findViewById(R.id.button)
        tvWinner.text = intent.getStringExtra("winner")
        btn.setOnClickListener() {
            finish()
        }
    }
}