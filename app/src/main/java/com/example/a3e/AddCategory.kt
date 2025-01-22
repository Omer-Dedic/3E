package com.example.a3e

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class AddCategory : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_category)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cancel: Button = findViewById(R.id.button)
        val save: Button = findViewById(R.id.button2)
        val delete: Button = findViewById(R.id.button5)
        val editText = findViewById<EditText>(R.id.editText2)
        val brisanjeKat = findViewById<TextInputEditText>(R.id.brisanjeKat)

        cancel.setOnClickListener{
            val intent = Intent(this, AddPage::class.java)
            startActivity(intent)
        }
        save.setOnClickListener{

            val nazivKategorije = editText.text.toString().trim()
            val dbHelper = Handler(this)

            if (nazivKategorije.isNotBlank()) {
                dbHelper.insertDataKat(nazivKategorije)
                Toast.makeText(this, "Kategorija sačuvana!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Unesite naziv kategorije!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, AddPage::class.java)
            startActivity(intent)
        }
        delete.setOnClickListener{
            val nazivZaBrisanje = brisanjeKat.text.toString().trim()

            if (nazivZaBrisanje.isEmpty()) {
                Toast.makeText(this, "Unesite naziv kategorije za brisanje!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val handler = Handler(this)

            if (handler.kategorijaPostoji(nazivZaBrisanje)) {
                // Prikaži dijalog za potvrdu
                val alertDialog = android.app.AlertDialog.Builder(this)
                    .setTitle("Potvrda brisanja")
                    .setMessage("Jeste li sigurni da želite obrisati kategoriju \"$nazivZaBrisanje\"? Svi povezani članovi će također biti obrisani.")
                    .setPositiveButton("Da") { _, _ ->
                        // Poziv funkcije za brisanje
                        val uspjeh = handler.deleteKategorija(nazivZaBrisanje)
                        if (uspjeh) {
                            Toast.makeText(this, "Kategorija i članovi uspješno obrisani!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, AddCategory::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Došlo je do greške pri brisanju kategorije.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .setNegativeButton("Ne") { dialog, _ ->
                        dialog.dismiss() // Zatvori dijalog
                    }
                    .create()

                alertDialog.show()
            } else {
                Toast.makeText(this, "Kategorija \"$nazivZaBrisanje\" ne postoji.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}