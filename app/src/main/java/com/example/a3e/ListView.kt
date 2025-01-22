package com.example.a3e

import android.content.Intent
import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val expandableListView: ExpandableListView = findViewById(R.id.expandableListView)

        val dbHandler = Handler(this)
        val childMap = HashMap<String, List<String>>(dbHandler.getCategoryWithMembers())
        val groupList = childMap.keys.toList()

        val adapter = CustomExpandableListAdapter(this, groupList, childMap)
        expandableListView.setAdapter(adapter)

        expandableListView.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val selectedChild = childMap[groupList[groupPosition]]?.get(childPosition)

            if (selectedChild == null) {
                Toast.makeText(this, "Greška: Nije pronađeno dijete!", Toast.LENGTH_SHORT).show()
                return@setOnChildClickListener true
            }

            val clanDetails = dbHandler.getClanDetails(selectedChild)

            if (clanDetails.ime.isEmpty() && clanDetails.prezime.isEmpty()) {
                Toast.makeText(this, "Greška: Podaci o članu nisu pronađeni!", Toast.LENGTH_SHORT).show()
                return@setOnChildClickListener true
            }

            val intent = Intent(this, PlayerView::class.java).apply {
                putExtra("idClan", clanDetails.idClan)
                putExtra("ime", clanDetails.ime)
                putExtra("prezime", clanDetails.prezime)
                putExtra("datumRodjenja", clanDetails.datumRodjenja)
                putExtra("kategorija", clanDetails.kategorija)
                putExtra("napomena", clanDetails.napomena)
            }
            startActivity(intent)
            true
        }

        val imageButton: ImageButton = findViewById(R.id.imageButton)

        imageButton.setOnClickListener{
            val intent = Intent(this, Pocetna::class.java)
            startActivity(intent)
        }
    }
}