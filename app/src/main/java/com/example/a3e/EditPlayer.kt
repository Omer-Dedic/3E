package com.example.a3e

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class EditPlayer : AppCompatActivity() {
    private lateinit var spinner1: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_player)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinner1 = findViewById(R.id.spinner1)

        val idClan = intent.getIntExtra("idClan", 0)
        val ime = intent.getStringExtra("ime")
        val prezime = intent.getStringExtra("prezime")
        val datumRodjenja = intent.getStringExtra("datumRodjenja")
        val kategorija = intent.getStringExtra("kategorija")?: ""
        val napomena = intent.getStringExtra("napomena")

        val dbHelper = Handler(this)
        val kategorije = dbHelper.getKategorije().toMutableList()

        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, kategorije)
        spinner1.adapter = adapter1

        val positionKategorija = kategorije.indexOf(kategorija)
        if (positionKategorija >= 0) {
            spinner1.setSelection(positionKategorija)
        }

        findViewById<TextInputEditText>(R.id.editIme).setText(ime)
        findViewById<TextInputEditText>(R.id.editPrezime).setText(prezime)
        findViewById<TextInputEditText>(R.id.editDatum).setText(datumRodjenja)
        findViewById<TextInputEditText>(R.id.editNapomena).setText(napomena)

        val cancel: Button = findViewById(R.id.button)
        val save: Button = findViewById(R.id.button2)

        cancel.setOnClickListener{
            val intent = Intent(this, PlayerView::class.java).apply {
                putExtra("idClan", idClan)
                putExtra("ime", ime)
                putExtra("prezime", prezime)
                putExtra("datumRodjenja", datumRodjenja)
                putExtra("kategorija", kategorija)
                putExtra("napomena", napomena)
            }
            startActivity(intent)
        }
        save.setOnClickListener{
            val intent = Intent(this, PlayerView::class.java).apply {
                var newIme = findViewById<TextInputEditText>(R.id.editIme).text.toString()
                var newPrezime = findViewById<TextInputEditText>(R.id.editPrezime).text.toString()
                var newDatumRodjenja = findViewById<TextInputEditText>(R.id.editDatum).text.toString()
                var newKategorija = spinner1.selectedItem.toString()
                var newNapomena = findViewById<TextInputEditText>(R.id.editNapomena).text.toString()

                if(newIme == ime){
                    newIme = ime
                }
                if(newPrezime == prezime){
                    newPrezime = prezime
                }
                if( newDatumRodjenja == datumRodjenja){
                    newDatumRodjenja = datumRodjenja
                }
                if(newKategorija == kategorija){
                    newKategorija = kategorija
                }
                if(newNapomena == napomena){
                    newNapomena = napomena
                }
                putExtra("idClan", idClan)
                putExtra("ime", newIme)
                putExtra("prezime", newPrezime)
                putExtra("datumRodjenja", newDatumRodjenja)
                putExtra("kategorija", newKategorija)
                putExtra("napomena", newNapomena)

                dbHelper.updateClan(idClan,newIme, newPrezime, newDatumRodjenja, newKategorija, newNapomena)
            }
            startActivity(intent)
        }
    }
}