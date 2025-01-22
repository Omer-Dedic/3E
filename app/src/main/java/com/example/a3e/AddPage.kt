package com.example.a3e

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddPage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageButton: ImageButton = findViewById(R.id.imageButton)
        val addPlayerbtn: Button = findViewById(R.id.button99)
        val imageButton1: ImageButton = findViewById(R.id.imageButton1)
        val flbtn: FloatingActionButton = findViewById(R.id.floatingActionButton)
        val addCategorybtn: Button = findViewById(R.id.button1)
        val uPKbtn: Button = findViewById(R.id.buttonUPK)

        uPKbtn.setOnClickListener{
            val intent = Intent(this,CategoryPaid::class.java)
            startActivity(intent)
        }

        imageButton.setOnClickListener{
            val intent = Intent(this, Pocetna::class.java)
            startActivity(intent)
        }

        imageButton1.setOnClickListener{
            val intent = Intent(this, ListView::class.java)
            startActivity(intent)
        }

        flbtn.setOnClickListener{
            val intent = Intent(this, Pocetna::class.java)
            startActivity(intent)
        }

        addPlayerbtn.setOnClickListener{
            val intent = Intent(this, AddPlayer::class.java)
            startActivity(intent)
        }

        addCategorybtn.setOnClickListener{
            val intent = Intent(this, AddCategory::class.java)
            startActivity(intent)
        }
    }
}