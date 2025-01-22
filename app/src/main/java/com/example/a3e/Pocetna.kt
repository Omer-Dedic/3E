package com.example.a3e

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton
import android.widget.ListPopupWindow
import android.widget.SearchView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Pocetna : AppCompatActivity() {
    private lateinit var dbHelper: Handler
    private lateinit var listPopupWindow: ListPopupWindow
    private lateinit var searchAdapter: ArrayAdapter<String>
    private val clanoviList = mutableListOf<Clan>()
    private val displayedList = mutableListOf<Clan>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pocetna)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = Handler(this)
        val searchBar: SearchView = findViewById(R.id.searchBar)
        clanoviList.addAll(dbHelper.getClanovi())
        displayedList.addAll(clanoviList)

        listPopupWindow = ListPopupWindow(this)
        searchAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayedList.map { it.ime ; it.prezime })
        listPopupWindow.setAdapter(searchAdapter)
        listPopupWindow.anchorView = searchBar

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val query = newText?.lowercase() ?: ""
                displayedList.clear()
                displayedList.addAll(clanoviList.filter {
                    it.ime.lowercase().contains(query) || it.prezime.lowercase().contains(query)
                })
                searchAdapter.clear()
                searchAdapter.addAll(displayedList.map { it.ime })
                searchAdapter.notifyDataSetChanged()
                return true
            }
        })

        listPopupWindow.setOnItemClickListener { _, _, position, _ ->
            val selectedClan = displayedList[position]

            val intent = Intent(this, PlayerView::class.java).apply {
                putExtra("idClan", selectedClan.idClan)
                putExtra("ime", selectedClan.ime)
                putExtra("prezime", selectedClan.prezime)
                putExtra("datumRodjenja", selectedClan.datumRodjenja)
                putExtra("kategorija", selectedClan.kategorija)
                putExtra("napomena", selectedClan.napomena)
            }
            startActivity(intent)

            listPopupWindow.dismiss()
        }

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchAndDisplayResults(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    searchAndDisplayResults(it)
                }
                return true
            }
        })

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
    private fun searchAndDisplayResults(query: String) {
        clanoviList.clear()
        clanoviList.addAll(dbHelper.searchClan(query))

        displayedList.clear()
        displayedList.addAll(clanoviList)
        searchAdapter.clear()
        searchAdapter.addAll(displayedList.map { "${it.ime} ${it.prezime}" })
        searchAdapter.notifyDataSetChanged()

        if (displayedList.isNotEmpty()) {
            listPopupWindow.show()
        } else {
            listPopupWindow.dismiss()
        }
    }
}