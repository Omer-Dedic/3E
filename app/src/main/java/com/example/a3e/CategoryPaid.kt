package com.example.a3e

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CategoryPaid : AppCompatActivity() {
    private lateinit var spinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_category_paid)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinner = findViewById(R.id.spinner)

        val dbHelper = Handler(this)
        val kategorije = mutableListOf("Izaberite opciju")
        kategorije.addAll(dbHelper.getKategorije())
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, kategorije)
        spinner.adapter = adapter

        val clanView = findViewById<TextView>(R.id.brclan)
        val uplView = findViewById<TextView>(R.id.upltxt)
        val neuplView = findViewById<TextView>(R.id.neupltxt)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val odabranaKategorija = parent?.getItemAtPosition(position).toString()

                if (odabranaKategorija == "Izaberite opciju" || odabranaKategorija.isEmpty()) {
                    clanView.text = "-"
                    uplView.text = "-"
                    neuplView.text = "-"
                    return
                }

                try {
                    val brojClanova = dbHelper.getBrojClanovaZaKategoriju(odabranaKategorija).toString()
                    clanView.text = brojClanova

                    val brojUplacenih = dbHelper.getBrojUplacenihClanarina(odabranaKategorija).toString()
                    uplView.text = brojUplacenih

                    val brojNeUplacenih = dbHelper.getBrojNeuplacenihClanarina(odabranaKategorija).toString()
                    neuplView.text = brojNeUplacenih
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val imageButton: ImageButton = findViewById(R.id.imageButton)
        val imageButton1: ImageButton = findViewById(R.id.imageButton1)
        val flbtn: FloatingActionButton = findViewById(R.id.floatingActionButton)

        imageButton.setOnClickListener{
            val intent = Intent(this, Pocetna::class.java)
            startActivity(intent)
        }

        imageButton1.setOnClickListener{
            val intent = Intent(this, ListView::class.java)
            startActivity(intent)
        }

        flbtn.setOnClickListener{
            val intent = Intent(this, AddPage::class.java)
            startActivity(intent)
        }
    }
}