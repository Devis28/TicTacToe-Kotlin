package com.example.tictactoek

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    var name1 = "O"
    var name2 = "X"
    var player1= true

    companion object {
        const val REQUEST_CODE = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val x = resources.getDrawable(R.drawable.x)
        val o = resources.getDrawable(R.drawable.o)


        val tv: TextView = findViewById(R.id.tv)
        val btnName: Button = findViewById(R.id.changeName)

        val btn1: ImageButton = findViewById(R.id.imageButton1)
        val btn2: ImageButton = findViewById(R.id.imageButton2)
        val btn3: ImageButton = findViewById(R.id.imageButton3)
        val btn4: ImageButton = findViewById(R.id.imageButton4)
        val btn5: ImageButton = findViewById(R.id.imageButton5)
        val btn6: ImageButton = findViewById(R.id.imageButton6)
        val btn7: ImageButton = findViewById(R.id.imageButton7)
        val btn8: ImageButton = findViewById(R.id.imageButton8)
        val btn9: ImageButton = findViewById(R.id.imageButton9)

        btnName.setOnClickListener() {
            val nameIntent = Intent(this, ChangeNameActivity::class.java)
            nameIntent.putExtra("p1", name1)
            nameIntent.putExtra("p2", name2)
            startActivityForResult(nameIntent, REQUEST_CODE)
        }

        var pole = mutableListOf<ImageButton>(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

        for (i in 0..pole.size-1) {
            pole[i].setOnClickListener() {
                if (player1) {
                    if (jeVolne(pole[i])) {
                        pole[i].setImageDrawable(o)
                        pole[i].setTag("o")
                        checkWinner(pole[i], pole)
                        player1 = !player1
                    }
                } else {
                    if (jeVolne(pole[i])) {
                        pole[i].setImageDrawable(x)
                        pole[i].setTag("x")
                        checkWinner(pole[i], pole)
                        player1 = !player1
                    }
                }
                tv.setText(if (player1) (if (name1.isNullOrEmpty()) "O" else name1) else (if (name2.isNullOrEmpty()) "X" else name2))
            }
        }
    }

    fun jeVolne(pozicia: ImageButton): Boolean {
        val tag = pozicia.getTag()
        return tag == null
    }

    fun checkWinner(player: ImageButton, plocha: MutableList<ImageButton>) {
        val tag = player.getTag().toString()
        var win = false
        var i = 0
        while (i <= 6) {
            if ((plocha[i].getTag()!=null && plocha[i].getTag().toString().equals(tag)) && (plocha[i+1].getTag()!=null && plocha[i+1].getTag().toString().equals(tag)) && (plocha[i+2].getTag()!=null && plocha[i+2].getTag().toString().equals(tag))) {
                win = true
                break
            }
            i += 3
        }
        for (i in 0..2) {
            if ((plocha[i].getTag()!=null && plocha[i].getTag().toString().equals(tag)) && (plocha[i+3].getTag()!=null && plocha[i+3].getTag().toString().equals(tag)) && (plocha[i+6].getTag()!=null && plocha[i+6].getTag().toString().equals(tag))) {
                win = true
                break
            }
        }

        if ((plocha[0].getTag()!=null && plocha[0].getTag().toString().equals(tag)) && (plocha[4].getTag()!=null && plocha[4].getTag().toString().equals(tag)) && (plocha[8].getTag()!=null && plocha[8].getTag().toString().equals(tag))) {
            win = true
        }

        if ((plocha[2].getTag()!=null && plocha[2].getTag().toString().equals(tag)) && (plocha[4].getTag()!=null && plocha[4].getTag().toString().equals(tag)) && (plocha[6].getTag()!=null && plocha[6].getTag().toString().equals(tag))) {
            win = true
        }

        var draw = 0
        for (i in 0..8) {
            if (plocha[i].getTag()!=null) {
                draw++
            }
        }

        if (win && draw != 9) {
            val winIntent = Intent(this, WinActivity::class.java)
            winIntent.putExtra("winner", "Víťaz \n" + (if (name1.isNullOrEmpty() || name2.isNullOrEmpty()) tag.uppercase() else whoIsWinner(tag)))
            startActivity(winIntent)
            reset(plocha)
        }
        else if (draw == 9) {
            val winIntent = Intent(this, WinActivity::class.java)
            winIntent.putExtra("winner", "REMÍZA")
            startActivity(winIntent)
            reset(plocha)
        }
    }

    fun whoIsWinner(tag: String): String {
        return if (tag == "o") name1 else name2
    }

    fun reset(plocha: MutableList<ImageButton>) {
        for (i in 0..8) {
            plocha[i].setImageDrawable(null)
            plocha[i].setTag(null)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            name1 = data?.getStringExtra("p1") ?: "O"
            name2 = data?.getStringExtra("p2") ?: "X"
            val tv: TextView = findViewById(R.id.tv)
            tv.setText(if (player1) (if (name1.isNullOrEmpty()) "O" else name1) else (if (name2.isNullOrEmpty()) "X" else name2))
        }
    }

}