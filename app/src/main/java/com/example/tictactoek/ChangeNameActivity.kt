package com.example.tictactoek

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class ChangeNameActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_name)

        val p1name: EditText = findViewById(R.id.p1name)
        val p2name: EditText = findViewById(R.id.p2name)

        p1name.setText(intent.getStringExtra("p1"))
        p2name.setText(intent.getStringExtra("p2"))

        val confirmBtn: Button = findViewById(R.id.confirmBtn)

        //p1name.setText(if (intentt.getStringExtra("p1").isNullOrEmpty()) "" else intentt.getStringExtra("p1"))
        //p2name.setText(if (intentt.getStringExtra("p2").isNullOrEmpty()) "" else intentt.getStringExtra("p2"))

        confirmBtn.setOnClickListener() {
            val resultIntent = Intent()
            resultIntent.putExtra("p1", p1name.text.toString())
            resultIntent.putExtra("p2", p2name.text.toString())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

    }
}