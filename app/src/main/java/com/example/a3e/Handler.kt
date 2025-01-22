package com.example.a3e

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val DATABASE_NAME = "Baza"
val TABLE_NAME = "Kategorija"
val COL_ID= "idKategorija"
val COL_NAZIV = "naziv"
val COL_CLAN = "Clan"
val COL_IDCLAN = "idClan"
val COL_IME = "ime"
val COL_PREZ = "prezime"
val COL_DAT = "datumRodjenja"
val COL_KAT = "kategorija"
val COL_NAP = "napomena"

class Handler( var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,31){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """CREATE TABLE $TABLE_NAME  (
                $COL_ID  INTEGER PRIMARY KEY AUTOINCREMENT, 
                $COL_NAZIV  VARCHAR(255) 
        ); """


        val createClanTable = """CREATE TABLE $COL_CLAN ( 
                $COL_IDCLAN  INTEGER PRIMARY KEY AUTOINCREMENT, 
                $COL_IME  VARCHAR(255),
                $COL_PREZ VARCHAR(255),
                $COL_DAT  VARCHAR(255),
                $COL_KAT VARCHAR(255),
                $COL_NAP  VARCHAR(255) DEFAULT null
        );"""

        val createUplataTable = """
            CREATE TABLE Uplata (
                idUplata INTEGER PRIMARY KEY AUTOINCREMENT,
                idClan INTEGER NOT NULL,
                JanUpl DATE DEFAULT NULL,
                JanIst DATE DEFAULT NULL,
                FebUpl DATE DEFAULT NULL,
                FebIst DATE DEFAULT NULL,
                MarUpl DATE DEFAULT NULL,
                MarIst DATE DEFAULT NULL,
                AprUpl DATE DEFAULT NULL,
                AprIst DATE DEFAULT NULL,
                MajUpl DATE DEFAULT NULL,
                MajIst DATE DEFAULT NULL,
                JunUpl DATE DEFAULT NULL,
                JunIst DATE DEFAULT NULL,
                JulUpl DATE DEFAULT NULL,
                JulIst DATE DEFAULT NULL,
                AugUpl DATE DEFAULT NULL,
                AugIst DATE DEFAULT NULL,
                SepUpl DATE DEFAULT NULL,
                SepIst DATE DEFAULT NULL,
                OktUpl DATE DEFAULT NULL,
                OktIst DATE DEFAULT NULL,
                NovUpl DATE DEFAULT NULL,
                NovIst DATE DEFAULT NULL,
                DecUpl DATE DEFAULT NULL,
                DecIst DATE DEFAULT NULL,
                FOREIGN KEY (idClan) REFERENCES Clan(idClan) ON DELETE CASCADE
            );"""

        db?.execSQL(createTable)
        db?.execSQL(createClanTable)
        db?.execSQL(createUplataTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db?.execSQL("DROP TABLE IF EXISTS $COL_CLAN")
        db?.execSQL("DROP TABLE IF EXISTS Clanarina")
        db?.execSQL("DROP TABLE IF EXISTS Uplata")
        onCreate(db)
        db?.execSQL("""
            INSERT INTO Uplata (idClan)
            SELECT idClan
            FROM $COL_CLAN
            WHERE $COL_KAT IS NOT NULL;
        """)
    }

    fun insertDataKat (naziv: String){
        val db = this.writableDatabase
        var cv = ContentValues().apply {
            put(COL_NAZIV, naziv)
        }

        var result = db.insert(TABLE_NAME,null,cv)
        if(result == (-1).toLong()){
            Toast.makeText(context,"Greška u konekciji sa bazom", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context,"Kategorija uspješno unesena", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun insertDataClan (ime: String, prezime: String, datumRodjenja: String, kategorija: String, napomena: String) {
        val db = this.writableDatabase
        var cv = ContentValues().apply {
            put(COL_IME, ime)
            put(COL_PREZ, prezime)
            put(COL_DAT, datumRodjenja)
            put(COL_KAT, kategorija)
            put(COL_NAP, napomena)
        }

        var provjera = db.insert(COL_CLAN,null,cv)
        if(provjera == (-1).toLong()){
            Toast.makeText(context,"Greška pri komunikaciji sa bazom", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context,"Uspješno dodan član", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun insertDataUplata( idClan: Int, JanUpl: Date?, JanIst: Date?, FebUpl: Date?, FebIst: Date?,
        MarUpl: Date?,
        MarIst: Date?,
        AprUpl: Date?,
        AprIst: Date?,
        MajUpl: Date?,
        MajIst: Date?,
        JunUpl: Date?,
        JunIst: Date?,
        JulUpl: Date?,
        JulIst: Date?,
        AugUpl: Date?,
        AugIst: Date?,
        SepUpl: Date?,
        SepIst: Date?,
        OktUpl: Date?,
        OktIst: Date?,
        NovUpl: Date?,
        NovIst: Date?,
        DecUpl: Date?,
        DecIst: Date?
    ){
        val db = this.writableDatabase
        val dateFormat = java.text.SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        val cv = ContentValues().apply {
            put("idClan", idClan)
            put("JanUpl", JanUpl?.let { dateFormat.format(it) })
            put("JanIst", JanIst?.let { dateFormat.format(it) })
            put("FebUpl", FebUpl?.let { dateFormat.format(it) })
            put("FebIst", FebIst?.let { dateFormat.format(it) })
            put("MarUpl", MarUpl?.let { dateFormat.format(it) })
            put("MarIst", MarIst?.let { dateFormat.format(it) })
            put("AprUpl", AprUpl?.let { dateFormat.format(it) })
            put("AprIst", AprIst?.let { dateFormat.format(it) })
            put("MajUpl", MajUpl?.let { dateFormat.format(it) })
            put("MajIst", MajIst?.let { dateFormat.format(it) })
            put("JunUpl", JunUpl?.let { dateFormat.format(it) })
            put("JunIst", JunIst?.let { dateFormat.format(it) })
            put("JulUpl", JulUpl?.let { dateFormat.format(it) })
            put("JulIst", JulIst?.let { dateFormat.format(it) })
            put("AugUpl", AugUpl?.let { dateFormat.format(it) })
            put("AugIst", AugIst?.let { dateFormat.format(it) })
            put("SepUpl", SepUpl?.let { dateFormat.format(it) })
            put("SepIst", SepIst?.let { dateFormat.format(it) })
            put("OktUpl", OktUpl?.let { dateFormat.format(it) })
            put("OktIst", OktIst?.let { dateFormat.format(it) })
            put("NovUpl", NovUpl?.let { dateFormat.format(it) })
            put("NovIst", NovIst?.let { dateFormat.format(it) })
            put("DecUpl", DecUpl?.let { dateFormat.format(it) })
            put("DecIst", DecIst?.let { dateFormat.format(it) })
        }

        val result = db.insert("Uplata", null, cv)
        if (result == (-1).toLong()) {
            Toast.makeText(context, "Greška pri komunikaciji sa bazom", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Uspješno dodata uplata", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun updateDataUplata(
        idClan: Int,
        JanUpl: Date?, JanIst: Date?, FebUpl: Date?, FebIst: Date?,
        MarUpl: Date?, MarIst: Date?, AprUpl: Date?, AprIst: Date?,
        MajUpl: Date?, MajIst: Date?, JunUpl: Date?, JunIst: Date?,
        JulUpl: Date?, JulIst: Date?, AugUpl: Date?, AugIst: Date?,
        SepUpl: Date?, SepIst: Date?, OktUpl: Date?, OktIst: Date?,
        NovUpl: Date?, NovIst: Date?, DecUpl: Date?, DecIst: Date?
    ): Boolean {
        val db = this.writableDatabase
        val dateFormat = java.text.SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        val cv = ContentValues().apply {
            if (JanUpl != null) put("JanUpl", dateFormat.format(JanUpl))
            if (JanIst != null) put("JanIst", dateFormat.format(JanIst))
            if (FebUpl != null) put("FebUpl", dateFormat.format(FebUpl))
            if (FebIst != null) put("FebIst", dateFormat.format(FebIst))
            if (MarUpl != null) put("MarUpl", dateFormat.format(MarUpl))
            if (MarIst != null) put("MarIst", dateFormat.format(MarIst))
            if (AprUpl != null) put("AprUpl", dateFormat.format(AprUpl))
            if (AprIst != null) put("AprIst", dateFormat.format(AprIst))
            if (MajUpl != null) put("MajUpl", dateFormat.format(MajUpl))
            if (MajIst != null) put("MajIst", dateFormat.format(MajIst))
            if (JunUpl != null) put("JunUpl", dateFormat.format(JunUpl))
            if (JunIst != null) put("JunIst", dateFormat.format(JunIst))
            if (JulUpl != null) put("JulUpl", dateFormat.format(JulUpl))
            if (JulIst != null) put("JulIst", dateFormat.format(JulIst))
            if (AugUpl != null) put("AugUpl", dateFormat.format(AugUpl))
            if (AugIst != null) put("AugIst", dateFormat.format(AugIst))
            if (SepUpl != null) put("SepUpl", dateFormat.format(SepUpl))
            if (SepIst != null) put("SepIst", dateFormat.format(SepIst))
            if (OktUpl != null) put("OktUpl", dateFormat.format(OktUpl))
            if (OktIst != null) put("OktIst", dateFormat.format(OktIst))
            if (NovUpl != null) put("NovUpl", dateFormat.format(NovUpl))
            if (NovIst != null) put("NovIst", dateFormat.format(NovIst))
            if (DecUpl != null) put("DecUpl", dateFormat.format(DecUpl))
            if (DecIst != null) put("DecIst", dateFormat.format(DecIst))
        }

        val result = db.update("Uplata", cv, "idClan = ?", arrayOf(idClan.toString()))
        db.close()

        return result > 0
    }

    fun getNajveciDatum(idClan: Int): Date? {
        val db = this.readableDatabase
        val query = """
        SELECT JanUpl, JanIst, FebUpl, FebIst, MarUpl, MarIst,
               AprUpl, AprIst, MajUpl, MajIst, JunUpl, JunIst,
               JulUpl, JulIst, AugUpl, AugIst, SepUpl, SepIst,
               OktUpl, OktIst, NovUpl, NovIst, DecUpl, DecIst
        FROM Uplata
        WHERE idClan = ?
    """
        val cursor = db.rawQuery(query, arrayOf(idClan.toString()))
        val dateFormat = java.text.SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val sviDatumi = mutableListOf<Date>()

        if (cursor.moveToFirst()) {
            val columns = arrayOf(
                "JanUpl", "JanIst", "FebUpl", "FebIst", "MarUpl", "MarIst",
                "AprUpl", "AprIst", "MajUpl", "MajIst", "JunUpl", "JunIst",
                "JulUpl", "JulIst", "AugUpl", "AugIst", "SepUpl", "SepIst",
                "OktUpl", "OktIst", "NovUpl", "NovIst", "DecUpl", "DecIst"
            )

            for (column in columns) {
                val dateString = cursor.getString(cursor.getColumnIndexOrThrow(column))
                if (!dateString.isNullOrEmpty()) {
                    try {
                        val date = dateFormat.parse(dateString)
                        if (date != null) {
                            sviDatumi.add(date)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

        cursor.close()
        db.close()

        return sviDatumi.maxOrNull()
    }

    fun getBrojClanovaZaKategoriju(kategorija: String): Int {
        val db = this.readableDatabase
        val query = "SELECT COUNT(*) FROM Clan WHERE kategorija = ?"
        val cursor = db.rawQuery(query, arrayOf(kategorija))
        var brojClanova = 0

        if (cursor.moveToFirst()) {
            brojClanova = cursor.getInt(0)
        }

        cursor.close()
        db.close()

        return brojClanova
    }

    fun getBrojUplacenihClanarina(kategorija: String): Int {
        val db = this.readableDatabase
        val trenutniDatum = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val query = """
        SELECT COUNT(*)
        FROM Clan
        WHERE Kategorija = ? AND idClan IN (
            SELECT idClan
            FROM Uplata
            WHERE (
                JanIst >= ? OR FebIst >= ? OR MarIst >= ? OR AprIst >= ? OR 
                MajIst >= ? OR JunIst >= ? OR JulIst >= ? OR AugIst >= ? OR 
                SepIst >= ? OR OktIst >= ? OR NovIst >= ? OR DecIst >= ?
            )
        )
    """

        val args = arrayOf(kategorija, trenutniDatum, trenutniDatum, trenutniDatum, trenutniDatum,
            trenutniDatum, trenutniDatum, trenutniDatum, trenutniDatum, trenutniDatum,
            trenutniDatum, trenutniDatum, trenutniDatum)

        var broj = 0
        val cursor = db.rawQuery(query, args)
        if (cursor.moveToFirst()) {
            broj = cursor.getInt(0)
        }
        cursor.close()
        db.close()
        return broj
    }

    fun getBrojNeuplacenihClanarina(kategorija: String): Int {
        val db = this.readableDatabase
        val trenutniDatum = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        val query = """
        SELECT COUNT(*)
        FROM Clan
        WHERE Kategorija = ? AND idClan IN (
            SELECT idClan
            FROM Uplata
            WHERE (
                JanIst < ? AND FebIst < ? AND MarIst < ? AND AprIst < ? AND 
                MajIst < ? AND JunIst < ? AND JulIst < ? AND AugIst < ? AND 
                SepIst < ? AND OktIst < ? AND NovIst < ? AND DecIst < ?
            ) OR (
                JanIst IS NULL AND FebIst IS NULL AND MarIst IS NULL AND AprIst IS NULL AND 
                MajIst IS NULL AND JunIst IS NULL AND JulIst IS NULL AND AugIst IS NULL AND 
                SepIst IS NULL AND OktIst IS NULL AND NovIst IS NULL AND DecIst IS NULL
            )
        )
    """

        val args = arrayOf(kategorija, trenutniDatum, trenutniDatum, trenutniDatum, trenutniDatum,
            trenutniDatum, trenutniDatum, trenutniDatum, trenutniDatum, trenutniDatum,
            trenutniDatum, trenutniDatum, trenutniDatum)

        var broj = 0
        val cursor = db.rawQuery(query, args)
        if (cursor.moveToFirst()) {
            broj = cursor.getInt(0)
        }
        cursor.close()
        db.close()
        return broj
    }

    fun getDataUplata(idClan: Int, columnName: String): String? {
        val db = this.readableDatabase
        val cursor = db.query(
            "Uplata",
            arrayOf(columnName),
            "idClan = ?",
            arrayOf(idClan.toString()),
            null,
            null,
            null
        )

        var result: String? = null
        if (cursor.moveToFirst()) {
            result = cursor.getString(0)
        }
        cursor.close()
        db.close()
        return result
    }

    fun resetUplataTable() {
        val db = this.writableDatabase

        try {
            db.execSQL("UPDATE Uplata SET " +
                    "JanUpl = NULL, JanIst = NULL, " +
                    "FebUpl = NULL, FebIst = NULL, " +
                    "MarUpl = NULL, MarIst = NULL, " +
                    "AprUpl = NULL, AprIst = NULL, " +
                    "MajUpl = NULL, MajIst = NULL, " +
                    "JunUpl = NULL, JunIst = NULL, " +
                    "JulUpl = NULL, JulIst = NULL, " +
                    "AugUpl = NULL, AugIst = NULL, " +
                    "SepUpl = NULL, SepIst = NULL, " +
                    "OktUpl = NULL, OktIst = NULL, " +
                    "NovUpl = NULL, NovIst = NULL, " +
                    "DecUpl = NULL, DecIst = NULL;")

            Toast.makeText(context, "Tabele uplata uspjesno resetovane", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Greška pri resetovanju tabela uplata", Toast.LENGTH_SHORT).show()
        } finally {
            db.close()
        }
    }

    fun getKategorije(): List<String> {
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, arrayOf(COL_NAZIV),null,null,null,null,null)

        val kategorije = mutableListOf<String>()
        if(cursor.moveToFirst()) {
            do {
                val categories = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAZIV))
                kategorije.add(categories)
            }while (cursor.moveToNext())
        }
        cursor.close()
        return kategorije
    }

    fun getCategoryWithMembers(): Map<String, List<String>> {
        val db = readableDatabase
        val childMap = mutableMapOf<String, List<String>>()

        val categoryCursor = db.query(TABLE_NAME, arrayOf(COL_ID, COL_NAZIV), null, null, null, null, null)
        if (categoryCursor.moveToFirst()) {
            do {
                val categoryName = categoryCursor.getString(categoryCursor.getColumnIndexOrThrow(COL_NAZIV))

                val memberCursor = db.query(
                    COL_CLAN,
                    arrayOf(COL_IME, COL_PREZ),
                    "$COL_KAT = ?",
                    arrayOf(categoryName),
                    null,
                    null,
                    null
                )

                val members = mutableListOf<String>()
                if (memberCursor.moveToFirst()) {
                    do {
                        val memberName = memberCursor.getString(memberCursor.getColumnIndexOrThrow(COL_IME))
                        val memberLastName = memberCursor.getString(memberCursor.getColumnIndexOrThrow(COL_PREZ))
                        members.add("$memberName $memberLastName")
                    } while (memberCursor.moveToNext())
                }
                memberCursor.close()

                childMap[categoryName] = members
            } while (categoryCursor.moveToNext())
        }
        categoryCursor.close()
        return childMap
    }

    fun getClanDetails(childFullName: String): Clan {
        val db = this.readableDatabase

        val cursor = db.query(
            COL_CLAN,
            arrayOf(COL_IDCLAN, COL_IME, COL_PREZ, COL_DAT, COL_KAT, COL_NAP),
            "$COL_IME || ' ' || $COL_PREZ = ?",
            arrayOf(childFullName),
            null, null, null
        )

        var clanDetails = Clan(0, "", "", "", "", "")

        if (cursor.moveToFirst()) {
            val idClanIndex = cursor.getColumnIndex(COL_IDCLAN)
            val idClan = if (idClanIndex >= 0) cursor.getInt(idClanIndex) else 0

            val imeIndex = cursor.getColumnIndex(COL_IME)
            val prezimeIndex = cursor.getColumnIndex(COL_PREZ)
            val datumRodjenjaIndex = cursor.getColumnIndex(COL_DAT)
            val kategorijaIndex = cursor.getColumnIndex(COL_KAT)
            val napomenaIndex = cursor.getColumnIndex(COL_NAP)

            val ime = if (imeIndex >= 0) cursor.getString(imeIndex) else ""
            val prezime = if (prezimeIndex >= 0) cursor.getString(prezimeIndex) else ""
            val datumRodjenja = if (datumRodjenjaIndex >= 0) cursor.getString(datumRodjenjaIndex) else ""
            val kategorija = if (kategorijaIndex >= 0) cursor.getString(kategorijaIndex) else ""
            val napomena = if (napomenaIndex >= 0) cursor.getString(napomenaIndex) else ""

            clanDetails = Clan(
                idClan,
                ime,
                prezime,
                datumRodjenja,
                kategorija,
                napomena
            )
        }
        cursor.close()
        return clanDetails
    }

    fun updateClan(idClan: Int, ime: String, prezime: String, datumRodjenja: String, kategorija: String, napomena: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COL_IDCLAN , idClan)
            put(COL_IME, ime)
            put(COL_PREZ, prezime)
            put(COL_DAT, datumRodjenja)
            put(COL_KAT, kategorija)
            put(COL_NAP, napomena)
        }
        val result = db.update(COL_CLAN, contentValues, "$COL_IDCLAN = ?", arrayOf(idClan.toString()))
        db.close()
        return result > 0
    }

    fun deleteClan(idClan: Int): Boolean {
        val db = this.writableDatabase
        return db.delete(COL_CLAN, "$COL_IDCLAN = ?", arrayOf(idClan.toString())) > 0
    }

    fun deleteUplataByClanId(clanId: Int): Boolean {
        val db = this.writableDatabase
         return db.delete(
                "Uplata",
                "idClan = ?",
                arrayOf(clanId.toString())
            ) >= 0
    }

    fun kategorijaPostoji(nazivKategorije: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COL_ID),
            "$COL_NAZIV = ?",
            arrayOf(nazivKategorije),
            null,
            null,
            null
        )
        val postoji = cursor.count > 0
        cursor.close()
        db.close()
        return postoji
    }

    fun deleteKategorija(nazivKategorije: String): Boolean {
        val db = this.writableDatabase
        return try {
            db.delete(COL_CLAN, "$COL_KAT = ?", arrayOf(nazivKategorije))

            val rowsDeleted = db.delete(TABLE_NAME, "$COL_NAZIV = ?", arrayOf(nazivKategorije))

            db.close()
            rowsDeleted > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun searchClan(query: String): List<Clan> {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $COL_CLAN WHERE $COL_IME LIKE ? OR $COL_PREZ LIKE ?",
            arrayOf("%$query%", "%$query%")
        )

        val clanovi = mutableListOf<Clan>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_IDCLAN))
                val ime = cursor.getString(cursor.getColumnIndexOrThrow(COL_IME))
                val prezime = cursor.getString(cursor.getColumnIndexOrThrow(COL_PREZ))
                val datum = cursor.getString(cursor.getColumnIndexOrThrow(COL_DAT))
                val kategorija = cursor.getString(cursor.getColumnIndexOrThrow(COL_KAT))
                val napomena = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAP))
                clanovi.add(Clan(id, ime, prezime, datum, kategorija, napomena))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return clanovi
    }

    @SuppressLint("Range")
    fun getClanovi(): List<Clan> {
        val clanovi = mutableListOf<Clan>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $COL_CLAN", null)

        if (cursor.moveToFirst()) {
            do {
                val clan = Clan(
                    idClan = cursor.getInt(cursor.getColumnIndex(COL_IDCLAN)),
                    ime = cursor.getString(cursor.getColumnIndex(COL_IME)),
                    prezime = cursor.getString(cursor.getColumnIndex(COL_PREZ)),
                    datumRodjenja = cursor.getString(cursor.getColumnIndex(COL_DAT)),
                    kategorija = cursor.getString(cursor.getColumnIndex(COL_KAT)),
                    napomena = cursor.getString(cursor.getColumnIndex(COL_NAP))
                )
                clanovi.add(clan)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return clanovi
    }

    fun getLastCreatedClan(): Int? {
        val db = this.readableDatabase
        val query = "SELECT idClan FROM Clan ORDER BY idClan DESC LIMIT 1"
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            cursor.getInt(cursor.getColumnIndexOrThrow("idClan"))
        } else {
            null
        }.also {
            cursor.close()
            db.close()
        }
    }
}
