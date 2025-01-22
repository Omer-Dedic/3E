package com.example.a3e

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class AddPlayer : AppCompatActivity() {
    private lateinit var spinner1: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_player)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinner1 = findViewById(R.id.spinner1)

        val imeInput = findViewById<TextInputEditText>(R.id.imeeInput)
        val prezimeInput = findViewById<TextInputEditText>(R.id.prezimeeInput)
        val dateInput: EditText = findViewById(R.id.datummInput)
        val napomenaInput = findViewById<TextInputEditText>(R.id.napomenaaInput)

        val dbHelper = Handler(this)
        val kategorije = mutableListOf("Izaberite opciju")
        kategorije.addAll(dbHelper.getKategorije())

        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, kategorije)
        spinner1.adapter = adapter1

        val cancel: Button = findViewById(R.id.button)
        val save: Button = findViewById(R.id.button2)

        dateInput.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false
            private val dateFormat = "##-##-####"

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isUpdating) return

                val currentText = s.toString().replace("-", "")
                val formattedText = StringBuilder()

                var i = 0
                for (char in dateFormat) {
                    if (i >= currentText.length) break
                    if (char == '#') {
                        formattedText.append(currentText[i])
                        i++
                    } else {
                        formattedText.append(char)
                    }
                }

                isUpdating = true
                dateInput.setText(formattedText.toString())
                dateInput.setSelection(formattedText.length) // Postavi kursor na kraj
                isUpdating = false
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        cancel.setOnClickListener {
            val intent = Intent(this, AddPage::class.java)
            startActivity(intent)
        }

        save.setOnClickListener {
            val imeClana = imeInput.text.toString().trim()
            val prezimeClana = prezimeInput.text.toString().trim()
            val datumClana = dateInput.text.toString().trim()
            val kategorija = spinner1.selectedItem.toString()
            val napomenaClana = napomenaInput.text.toString().trim()
            val JanUpl = null
            val JanIst = null
            val FebUpl = null
            val FebIst = null
            val MarUpl = null
            val MarIst = null
            val AprUpl = null
            val AprIst = null
            val MajUpl = null
            val MajIst = null
            val JunUpl = null
            val JunIst = null
            val JulUpl = null
            val JulIst = null
            val AugUpl = null
            val AugIst = null
            val SepUpl = null
            val SepIst = null
            val OktUpl = null
            val OktIst = null
            val NovUpl = null
            val NovIst = null
            val DecUpl = null
            val DecIst = null

            if (imeClana.isNotBlank() && prezimeClana.isNotBlank() && datumClana.isNotBlank() && kategorija != "Izaberite opciju") {
                    dbHelper.insertDataClan(imeClana, prezimeClana, datumClana, kategorija, napomenaClana)
                    val idClan = dbHelper.getLastCreatedClan()
                    if (idClan != null) {
                        dbHelper.insertDataUplata(idClan, JanUpl,
                            JanIst,
                            FebUpl,
                            FebIst,
                            MarUpl,
                            MarIst,
                            AprUpl,
                            AprIst,
                            MajUpl,
                            MajIst,
                            JunUpl,
                            JunIst,
                            JulUpl,
                            JulIst,
                            AugUpl,
                            AugIst,
                            SepUpl,
                            SepIst,
                            OktUpl,
                            OktIst,
                            NovUpl,
                            NovIst,
                            DecUpl,
                            DecIst )
                    }
                    val intent = Intent(this, AddPage::class.java)
                    startActivity(intent)
            } else {
                Toast.makeText(this, "Unesite sva polja!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
