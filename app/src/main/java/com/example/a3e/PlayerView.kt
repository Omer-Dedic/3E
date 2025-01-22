package com.example.a3e

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Locale

class PlayerView : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_player_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewIndicator = findViewById<View>(R.id.view)

        val dbHandler = Handler(this)

        val idClan = intent.getIntExtra("idClan", 0)
        val ime = intent.getStringExtra("ime")
        val prezime = intent.getStringExtra("prezime")
        val datumRodjenja = intent.getStringExtra("datumRodjenja")
        val kategorija = intent.getStringExtra("kategorija")
        val napomena = intent.getStringExtra("napomena")
        val maxdat = dbHandler.getNajveciDatum(idClan)

        val imePrezTextView = findViewById<TextView>(R.id.textView)
        val datumRodjenjaTextView = findViewById<TextView>(R.id.textView2)
        val clanarinaTextView = findViewById<TextView>(R.id.textView6)
        val kategorijaTextView = findViewById<TextView>(R.id.textView4)
        val napomenaTextView = findViewById<TextView>(R.id.textView8)

        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val currentDate = System.currentTimeMillis()

        if (maxdat == null){
            clanarinaTextView.text = "Nije unešen datum"
            viewIndicator.background = ContextCompat.getDrawable(this, R.drawable.ledred)
        } else {
            clanarinaTextView.text = dateFormat.format(maxdat)
            if (currentDate > maxdat.time) {
                viewIndicator.background = ContextCompat.getDrawable(this, R.drawable.ledred)
            }
        }

        imePrezTextView.text = "$ime $prezime"
        datumRodjenjaTextView.text = datumRodjenja
        kategorijaTextView.text = kategorija
        napomenaTextView.text = napomena

        val cancel: Button = findViewById(R.id.button)
        val imageButton: ImageButton = findViewById(R.id.imageButton)
        val imageButton1: ImageButton = findViewById(R.id.imageButton1)
        val flbtn: FloatingActionButton = findViewById(R.id.floatingActionButton)
        val edit: Button = findViewById(R.id.button1)
        val paid: Button = findViewById(R.id.button2)
        val delete: Button = findViewById(R.id.button3)

        cancel.setOnClickListener{
            val intent = Intent(this, ListView::class.java)
            startActivity(intent)
        }
        edit.setOnClickListener{
            val intent = Intent(this, EditPlayer::class.java).apply {
                putExtra("idClan", idClan)
                putExtra("ime", ime)
                putExtra("prezime", prezime)
                putExtra("datumRodjenja", datumRodjenja)
                putExtra("kategorija", kategorija)
                putExtra("napomena", napomena)
            }
            startActivity(intent)
        }
        paid.setOnClickListener{
            val intent = Intent(this, PaidPage::class.java).apply {
                putExtra("idClan", idClan)
                putExtra("ime", ime)
                putExtra("prezime", prezime)
                putExtra("datumRodjenja", datumRodjenja)
                putExtra("kategorija", kategorija)
                putExtra("napomena", napomena)
            }
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
        delete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Potvrda brisanja")
                .setMessage("Da li ste sigurni da želite obrisati ovog člana?")
                .setPositiveButton("Da") { _, _ ->
                    if (dbHandler.deleteClan(idClan) && dbHandler.deleteUplataByClanId(idClan)) {
                        Toast.makeText(this, "Član uspješno obrisan!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Greška pri brisanju člana!", Toast.LENGTH_SHORT).show()
                    }
                    val intent = Intent(this, ListView::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("Ne", null)
                .show()
        }
    }
}