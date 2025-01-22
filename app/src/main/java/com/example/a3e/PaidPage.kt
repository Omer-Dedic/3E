package com.example.a3e

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Suppress("CanBeVal", "LocalVariableName")
class PaidPage : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_paid_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dbHandler = Handler(this)
        val idClan = intent.getIntExtra("idClan", 0)
        val ime = intent.getStringExtra("ime")
        val prezime = intent.getStringExtra("prezime")
        val datumRodjenja = intent.getStringExtra("datumRodjenja")
        val kategorija = intent.getStringExtra("kategorija")
        val napomena = intent.getStringExtra("napomena")

        val cancel: Button = findViewById(R.id.button)
        val imageButton: ImageButton = findViewById(R.id.imageButton)
        val imageButton1: ImageButton = findViewById(R.id.imageButton1)
        val flbtn: FloatingActionButton = findViewById(R.id.floatingActionButton)

        val calendar = Calendar.getInstance()
        val tvPickDate1 = findViewById<TextView>(R.id.JanDat)

        val dateFormatInput = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val dateFormatOutput1 = SimpleDateFormat("dd", Locale.getDefault())
        val dateFormatOutput2 = SimpleDateFormat("dd.MM", Locale.getDefault())

        val janUplString = dbHandler.getDataUplata(idClan, "JanUpl")
        val janIstString = dbHandler.getDataUplata(idClan, "JanIst")

        if (janUplString != null && janIstString != null) {
            try {
                val uplDate = dateFormatInput.parse(janUplString)
                val istDate = dateFormatInput.parse(janIstString)

                val formattedDate1 = dateFormatOutput1.format(uplDate!!)
                val formattedDate2 = dateFormatOutput2.format(istDate!!)

                tvPickDate1.text = "$formattedDate1 - $formattedDate2"
            } catch (e: Exception) {
                e.printStackTrace()
                tvPickDate1.text = "Greška"
            }
        } else {
            tvPickDate1.text = "-"
        }

        tvPickDate1.setOnClickListener {
            DatePickerDialog(this, { _, year1, month1, day1 ->
                val firstDate = Calendar.getInstance()
                firstDate.set(year1, month1, day1)
                val JanUpl = firstDate.time

                DatePickerDialog(this, { _, year2, month2, day2 ->
                    val secondDate = Calendar.getInstance()
                    secondDate.set(year2, month2, day2)
                    val JanIst = secondDate.time

                    val formattedJanUpl = SimpleDateFormat("dd", Locale.getDefault()).format(JanUpl)
                    val formattedJanIst = SimpleDateFormat("dd.MM", Locale.getDefault()).format(JanIst)
                    tvPickDate1.text = "$formattedJanUpl - $formattedJanIst"

                    val success = dbHandler.updateDataUplata(
                        idClan,
                        JanUpl = JanUpl,
                        JanIst = JanIst,
                        FebUpl = null, FebIst = null, MarUpl = null, MarIst = null,
                        AprUpl = null, AprIst = null, MajUpl = null, MajIst = null,
                        JunUpl = null, JunIst = null, JulUpl = null, JulIst = null,
                        AugUpl = null, AugIst = null, SepUpl = null, SepIst = null,
                        OktUpl = null, OktIst = null, NovUpl = null, NovIst = null,
                        DecUpl = null, DecIst = null
                    )

                    if (success) {
                        Toast.makeText(this, "Podaci uspješno ažurirani", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Greška pri ažuriranju podataka", Toast.LENGTH_SHORT).show()
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val tvPickDate2 = findViewById<TextView>(R.id.FebDat)
        val febUplString = dbHandler.getDataUplata(idClan, "FebUpl")
        val febIstString = dbHandler.getDataUplata(idClan, "FebIst")

        if (febUplString != null && febIstString != null) {
            try {
                val uplDate = dateFormatInput.parse(febUplString)
                val istDate = dateFormatInput.parse(febIstString)

                val formattedDate1 = dateFormatOutput1.format(uplDate!!)
                val formattedDate2 = dateFormatOutput2.format(istDate!!)

                tvPickDate2.text = "$formattedDate1 - $formattedDate2"
            } catch (e: Exception) {
                e.printStackTrace()
                tvPickDate2.text = "Greška"
            }
        } else {
            tvPickDate2.text = "-"
        }

        tvPickDate2.setOnClickListener {
            DatePickerDialog(this, { _, year1, month1, day1 ->
                val firstDate = Calendar.getInstance()
                firstDate.set(year1, month1, day1)
                val FebUpl = firstDate.time

                DatePickerDialog(this, { _, year2, month2, day2 ->
                    val secondDate = Calendar.getInstance()
                    secondDate.set(year2, month2, day2)
                    val FebIst = secondDate.time

                    val formattedFebUpl = SimpleDateFormat("dd", Locale.getDefault()).format(FebUpl)
                    val formattedFebIst = SimpleDateFormat("dd.MM", Locale.getDefault()).format(FebIst)
                    tvPickDate2.text = "$formattedFebUpl - $formattedFebIst"

                    val success = dbHandler.updateDataUplata(
                        idClan,
                        JanUpl = null,
                        JanIst = null,
                        FebUpl = FebUpl, FebIst = FebIst, MarUpl = null, MarIst = null,
                        AprUpl = null, AprIst = null, MajUpl = null, MajIst = null,
                        JunUpl = null, JunIst = null, JulUpl = null, JulIst = null,
                        AugUpl = null, AugIst = null, SepUpl = null, SepIst = null,
                        OktUpl = null, OktIst = null, NovUpl = null, NovIst = null,
                        DecUpl = null, DecIst = null
                    )

                    if (success) {
                        Toast.makeText(this, "Podaci uspješno ažurirani", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Greška pri ažuriranju podataka", Toast.LENGTH_SHORT).show()
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val tvPickDate3 = findViewById<TextView>(R.id.MartDat)
        val marUplString = dbHandler.getDataUplata(idClan, "MarUpl")
        val marIstString = dbHandler.getDataUplata(idClan, "MarIst")

        if (marUplString != null && marIstString != null) {
            try {
                val uplDate = dateFormatInput.parse(marUplString)
                val istDate = dateFormatInput.parse(marIstString)

                val formattedDate1 = dateFormatOutput1.format(uplDate!!)
                val formattedDate2 = dateFormatOutput2.format(istDate!!)

                tvPickDate3.text = "$formattedDate1 - $formattedDate2"
            } catch (e: Exception) {
                e.printStackTrace()
                tvPickDate3.text = "Greška"
            }
        } else {
            tvPickDate3.text = "-"
        }

        tvPickDate3.setOnClickListener {
            DatePickerDialog(this, { _, year1, month1, day1 ->
                val firstDate = Calendar.getInstance()
                firstDate.set(year1, month1, day1)
                val MarUpl = firstDate.time

                DatePickerDialog(this, { _, year2, month2, day2 ->
                    val secondDate = Calendar.getInstance()
                    secondDate.set(year2, month2, day2)
                    val MarIst = secondDate.time

                    val formattedFebUpl = SimpleDateFormat("dd", Locale.getDefault()).format(MarUpl)
                    val formattedFebIst = SimpleDateFormat("dd.MM", Locale.getDefault()).format(MarIst)
                    tvPickDate3.text = "$formattedFebUpl - $formattedFebIst"

                    val success = dbHandler.updateDataUplata(
                        idClan,
                        JanUpl = null,
                        JanIst = null,
                        FebUpl = null, FebIst = null, MarUpl = MarUpl, MarIst = MarUpl,
                        AprUpl = null, AprIst = null, MajUpl = null, MajIst = null,
                        JunUpl = null, JunIst = null, JulUpl = null, JulIst = null,
                        AugUpl = null, AugIst = null, SepUpl = null, SepIst = null,
                        OktUpl = null, OktIst = null, NovUpl = null, NovIst = null,
                        DecUpl = null, DecIst = null
                    )

                    if (success) {
                        Toast.makeText(this, "Podaci uspješno ažurirani", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Greška pri ažuriranju podataka", Toast.LENGTH_SHORT).show()
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val tvPickDate4 = findViewById<TextView>(R.id.AprilDat)
        val aprUplString = dbHandler.getDataUplata(idClan, "AprUpl")
        val aprIstString = dbHandler.getDataUplata(idClan, "AprIst")

        if (aprUplString != null && aprIstString != null) {
            try {
                val uplDate = dateFormatInput.parse(aprUplString)
                val istDate = dateFormatInput.parse(aprIstString)

                val formattedDate1 = dateFormatOutput1.format(uplDate!!)
                val formattedDate2 = dateFormatOutput2.format(istDate!!)

                tvPickDate4.text = "$formattedDate1 - $formattedDate2"
            } catch (e: Exception) {
                e.printStackTrace()
                tvPickDate4.text = "Greška"
            }
        } else {
            tvPickDate4.text = "-"
        }

        tvPickDate4.setOnClickListener {
            DatePickerDialog(this, { _, year1, month1, day1 ->
                val firstDate = Calendar.getInstance()
                firstDate.set(year1, month1, day1)
                val AprUpl = firstDate.time

                DatePickerDialog(this, { _, year2, month2, day2 ->
                    val secondDate = Calendar.getInstance()
                    secondDate.set(year2, month2, day2)
                    val AprIst = secondDate.time

                    val formattedFebUpl = SimpleDateFormat("dd", Locale.getDefault()).format(AprUpl)
                    val formattedFebIst = SimpleDateFormat("dd.MM", Locale.getDefault()).format(AprIst)
                    tvPickDate4.text = "$formattedFebUpl - $formattedFebIst"

                    val success = dbHandler.updateDataUplata(
                        idClan,
                        JanUpl = null,
                        JanIst = null,
                        FebUpl = null, FebIst = null, MarUpl = null, MarIst = null,
                        AprUpl = AprUpl, AprIst = AprIst, MajUpl = null, MajIst = null,
                        JunUpl = null, JunIst = null, JulUpl = null, JulIst = null,
                        AugUpl = null, AugIst = null, SepUpl = null, SepIst = null,
                        OktUpl = null, OktIst = null, NovUpl = null, NovIst = null,
                        DecUpl = null, DecIst = null
                    )

                    if (success) {
                        Toast.makeText(this, "Podaci uspješno ažurirani", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Greška pri ažuriranju podataka", Toast.LENGTH_SHORT).show()
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val tvPickDate5 = findViewById<TextView>(R.id.MajDat)
        val majUplString = dbHandler.getDataUplata(idClan, "MajUpl")
        val majIstString = dbHandler.getDataUplata(idClan, "MajIst")

        if (majUplString != null && majIstString != null) {
            try {
                val uplDate = dateFormatInput.parse(majUplString)
                val istDate = dateFormatInput.parse(majIstString)

                val formattedDate1 = dateFormatOutput1.format(uplDate!!)
                val formattedDate2 = dateFormatOutput2.format(istDate!!)

                tvPickDate5.text = "$formattedDate1 - $formattedDate2"
            } catch (e: Exception) {
                e.printStackTrace()
                tvPickDate5.text = "Greška"
            }
        } else {
            tvPickDate5.text = "-"
        }

        tvPickDate5.setOnClickListener {
            DatePickerDialog(this, { _, year1, month1, day1 ->
                val firstDate = Calendar.getInstance()
                firstDate.set(year1, month1, day1)
                val MajUpl = firstDate.time

                DatePickerDialog(this, { _, year2, month2, day2 ->
                    val secondDate = Calendar.getInstance()
                    secondDate.set(year2, month2, day2)
                    val MajIst = secondDate.time

                    val formattedFebUpl = SimpleDateFormat("dd", Locale.getDefault()).format(MajUpl)
                    val formattedFebIst = SimpleDateFormat("dd.MM", Locale.getDefault()).format(MajIst)
                    tvPickDate5.text = "$formattedFebUpl - $formattedFebIst"

                    val success = dbHandler.updateDataUplata(
                        idClan,
                        JanUpl = null,
                        JanIst = null,
                        FebUpl = null, FebIst = null, MarUpl = null, MarIst = null,
                        AprUpl = null, AprIst = null, MajUpl = MajUpl, MajIst = MajIst,
                        JunUpl = null, JunIst = null, JulUpl = null, JulIst = null,
                        AugUpl = null, AugIst = null, SepUpl = null, SepIst = null,
                        OktUpl = null, OktIst = null, NovUpl = null, NovIst = null,
                        DecUpl = null, DecIst = null
                    )

                    if (success) {
                        Toast.makeText(this, "Podaci uspješno ažurirani", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Greška pri ažuriranju podataka", Toast.LENGTH_SHORT).show()
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val tvPickDate6 = findViewById<TextView>(R.id.JunDat)
        val junUplString = dbHandler.getDataUplata(idClan, "JunUpl")
        val junIstString = dbHandler.getDataUplata(idClan, "JunIst")

        if (junUplString != null && junIstString != null) {
            try {
                val uplDate = dateFormatInput.parse(junUplString)
                val istDate = dateFormatInput.parse(junIstString)

                val formattedDate1 = dateFormatOutput1.format(uplDate!!)
                val formattedDate2 = dateFormatOutput2.format(istDate!!)

                tvPickDate6.text = "$formattedDate1 - $formattedDate2"
            } catch (e: Exception) {
                e.printStackTrace()
                tvPickDate6.text = "Greška"
            }
        } else {
            tvPickDate6.text = "-"
        }

        tvPickDate6.setOnClickListener {
            DatePickerDialog(this, { _, year1, month1, day1 ->
                val firstDate = Calendar.getInstance()
                firstDate.set(year1, month1, day1)
                val JunUpl = firstDate.time

                DatePickerDialog(this, { _, year2, month2, day2 ->
                    val secondDate = Calendar.getInstance()
                    secondDate.set(year2, month2, day2)
                    val JunIst = secondDate.time

                    val formattedFebUpl = SimpleDateFormat("dd", Locale.getDefault()).format(JunUpl)
                    val formattedFebIst = SimpleDateFormat("dd.MM", Locale.getDefault()).format(JunIst)
                    tvPickDate6.text = "$formattedFebUpl - $formattedFebIst"

                    val success = dbHandler.updateDataUplata(
                        idClan,
                        JanUpl = null,
                        JanIst = null,
                        FebUpl = null, FebIst = null, MarUpl = null, MarIst = null,
                        AprUpl = null, AprIst = null, MajUpl = null, MajIst = null,
                        JunUpl = JunUpl, JunIst = JunIst, JulUpl = null, JulIst = null,
                        AugUpl = null, AugIst = null, SepUpl = null, SepIst = null,
                        OktUpl = null, OktIst = null, NovUpl = null, NovIst = null,
                        DecUpl = null, DecIst = null
                    )

                    if (success) {
                        Toast.makeText(this, "Podaci uspješno ažurirani", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Greška pri ažuriranju podataka", Toast.LENGTH_SHORT).show()
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val tvPickDate7 = findViewById<TextView>(R.id.JulDat)
        val julUplString = dbHandler.getDataUplata(idClan, "JulUpl")
        val julIstString = dbHandler.getDataUplata(idClan, "JulIst")

        if (julUplString != null && julIstString != null) {
            try {
                val uplDate = dateFormatInput.parse(julUplString)
                val istDate = dateFormatInput.parse(julIstString)

                val formattedDate1 = dateFormatOutput1.format(uplDate!!)
                val formattedDate2 = dateFormatOutput2.format(istDate!!)

                tvPickDate7.text = "$formattedDate1 - $formattedDate2"
            } catch (e: Exception) {
                e.printStackTrace()
                tvPickDate7.text = "Greška"
            }
        } else {
            tvPickDate7.text = "-"
        }

        tvPickDate7.setOnClickListener {
            DatePickerDialog(this, { _, year1, month1, day1 ->
                val firstDate = Calendar.getInstance()
                firstDate.set(year1, month1, day1)
                val JulUpl = firstDate.time

                DatePickerDialog(this, { _, year2, month2, day2 ->
                    val secondDate = Calendar.getInstance()
                    secondDate.set(year2, month2, day2)
                    val JulIst = secondDate.time

                    val formattedFebUpl = SimpleDateFormat("dd", Locale.getDefault()).format(JulUpl)
                    val formattedFebIst = SimpleDateFormat("dd.MM", Locale.getDefault()).format(JulIst)
                    tvPickDate7.text = "$formattedFebUpl - $formattedFebIst"

                    val success = dbHandler.updateDataUplata(
                        idClan,
                        JanUpl = null,
                        JanIst = null,
                        FebUpl = null, FebIst = null, MarUpl = null, MarIst = null,
                        AprUpl = null, AprIst = null, MajUpl = null, MajIst = null,
                        JunUpl = null, JunIst = null, JulUpl = JulUpl, JulIst = JulIst,
                        AugUpl = null, AugIst = null, SepUpl = null, SepIst = null,
                        OktUpl = null, OktIst = null, NovUpl = null, NovIst = null,
                        DecUpl = null, DecIst = null
                    )

                    if (success) {
                        Toast.makeText(this, "Podaci uspješno ažurirani", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Greška pri ažuriranju podataka", Toast.LENGTH_SHORT).show()
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val tvPickDate8 = findViewById<TextView>(R.id.AugDat)
        val augUplString = dbHandler.getDataUplata(idClan, "AugUpl")
        val augIstString = dbHandler.getDataUplata(idClan, "AugIst")

        if (augUplString != null && augIstString != null) {
            try {
                val uplDate = dateFormatInput.parse(augUplString)
                val istDate = dateFormatInput.parse(augIstString)

                val formattedDate1 = dateFormatOutput1.format(uplDate!!)
                val formattedDate2 = dateFormatOutput2.format(istDate!!)

                tvPickDate8.text = "$formattedDate1 - $formattedDate2"
            } catch (e: Exception) {
                e.printStackTrace()
                tvPickDate8.text = "Greška"
            }
        } else {
            tvPickDate8.text = "-"
        }

        tvPickDate8.setOnClickListener {
            DatePickerDialog(this, { _, year1, month1, day1 ->
                val firstDate = Calendar.getInstance()
                firstDate.set(year1, month1, day1)
                val AugUpl = firstDate.time

                DatePickerDialog(this, { _, year2, month2, day2 ->
                    val secondDate = Calendar.getInstance()
                    secondDate.set(year2, month2, day2)
                    val AugIst = secondDate.time

                    val formattedFebUpl = SimpleDateFormat("dd", Locale.getDefault()).format(AugUpl)
                    val formattedFebIst = SimpleDateFormat("dd.MM", Locale.getDefault()).format(AugIst)
                    tvPickDate8.text = "$formattedFebUpl - $formattedFebIst"

                    val success = dbHandler.updateDataUplata(
                        idClan,
                        JanUpl = null,
                        JanIst = null,
                        FebUpl = null, FebIst = null, MarUpl = null, MarIst = null,
                        AprUpl = null, AprIst = null, MajUpl = null, MajIst = null,
                        JunUpl = null, JunIst = null, JulUpl = null, JulIst = null,
                        AugUpl = AugUpl, AugIst = AugIst, SepUpl = null, SepIst = null,
                        OktUpl = null, OktIst = null, NovUpl = null, NovIst = null,
                        DecUpl = null, DecIst = null
                    )

                    if (success) {
                        Toast.makeText(this, "Podaci uspješno ažurirani", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Greška pri ažuriranju podataka", Toast.LENGTH_SHORT).show()
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val tvPickDate9 = findViewById<TextView>(R.id.SepDat)
        val sepUplString = dbHandler.getDataUplata(idClan, "SepUpl")
        val sepIstString = dbHandler.getDataUplata(idClan, "SepIst")

        if (sepUplString != null && sepIstString != null) {
            try {
                val uplDate = dateFormatInput.parse(sepUplString)
                val istDate = dateFormatInput.parse(sepIstString)

                val formattedDate1 = dateFormatOutput1.format(uplDate!!)
                val formattedDate2 = dateFormatOutput2.format(istDate!!)

                tvPickDate9.text = "$formattedDate1 - $formattedDate2"
            } catch (e: Exception) {
                e.printStackTrace()
                tvPickDate9.text = "Greška"
            }
        } else {
            tvPickDate9.text = "-"
        }

        tvPickDate9.setOnClickListener {
            DatePickerDialog(this, { _, year1, month1, day1 ->
                val firstDate = Calendar.getInstance()
                firstDate.set(year1, month1, day1)
                val SepUpl = firstDate.time

                DatePickerDialog(this, { _, year2, month2, day2 ->
                    val secondDate = Calendar.getInstance()
                    secondDate.set(year2, month2, day2)
                    val SepIst = secondDate.time

                    val formattedFebUpl = SimpleDateFormat("dd", Locale.getDefault()).format(SepUpl)
                    val formattedFebIst = SimpleDateFormat("dd.MM", Locale.getDefault()).format(SepIst)
                    tvPickDate9.text = "$formattedFebUpl - $formattedFebIst"

                    val success = dbHandler.updateDataUplata(
                        idClan,
                        JanUpl = null,
                        JanIst = null,
                        FebUpl = null, FebIst = null, MarUpl = null, MarIst = null,
                        AprUpl = null, AprIst = null, MajUpl = null, MajIst = null,
                        JunUpl = null, JunIst = null, JulUpl = null, JulIst = null,
                        AugUpl = null, AugIst = null, SepUpl = SepUpl, SepIst = SepIst,
                        OktUpl = null, OktIst = null, NovUpl = null, NovIst = null,
                        DecUpl = null, DecIst = null
                    )

                    if (success) {
                        Toast.makeText(this, "Podaci uspješno ažurirani", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Greška pri ažuriranju podataka", Toast.LENGTH_SHORT).show()
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val tvPickDate10 = findViewById<TextView>(R.id.OktDat)
        val oktUplString = dbHandler.getDataUplata(idClan, "OktUpl")
        val oktIstString = dbHandler.getDataUplata(idClan, "OktIst")

        if (oktUplString != null && oktIstString != null) {
            try {
                val uplDate = dateFormatInput.parse(oktUplString)
                val istDate = dateFormatInput.parse(oktIstString)

                val formattedDate1 = dateFormatOutput1.format(uplDate!!)
                val formattedDate2 = dateFormatOutput2.format(istDate!!)

                tvPickDate10.text = "$formattedDate1 - $formattedDate2"
            } catch (e: Exception) {
                e.printStackTrace()
                tvPickDate10.text = "Greška"
            }
        } else {
            tvPickDate10.text = "-"
        }

        tvPickDate10.setOnClickListener {
            DatePickerDialog(this, { _, year1, month1, day1 ->
                val firstDate = Calendar.getInstance()
                firstDate.set(year1, month1, day1)
                val OktUpl = firstDate.time

                DatePickerDialog(this, { _, year2, month2, day2 ->
                    val secondDate = Calendar.getInstance()
                    secondDate.set(year2, month2, day2)
                    val OktIst = secondDate.time

                    val formattedFebUpl = SimpleDateFormat("dd", Locale.getDefault()).format(OktUpl)
                    val formattedFebIst = SimpleDateFormat("dd.MM", Locale.getDefault()).format(OktIst)
                    tvPickDate10.text = "$formattedFebUpl - $formattedFebIst"

                    val success = dbHandler.updateDataUplata(
                        idClan,
                        JanUpl = null,
                        JanIst = null,
                        FebUpl = null, FebIst = null, MarUpl = null, MarIst = null,
                        AprUpl = null, AprIst = null, MajUpl = null, MajIst = null,
                        JunUpl = null, JunIst = null, JulUpl = null, JulIst = null,
                        AugUpl = null, AugIst = null, SepUpl = null, SepIst = null,
                        OktUpl = OktUpl, OktIst = OktIst, NovUpl = null, NovIst = null,
                        DecUpl = null, DecIst = null
                    )

                    if (success) {
                        Toast.makeText(this, "Podaci uspješno ažurirani", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Greška pri ažuriranju podataka", Toast.LENGTH_SHORT).show()
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val tvPickDate11 = findViewById<TextView>(R.id.NovDat)
        val novUplString = dbHandler.getDataUplata(idClan, "NovUpl")
        val novIstString = dbHandler.getDataUplata(idClan, "NovIst")

        if (novUplString != null && novIstString != null) {
            try {
                val uplDate = dateFormatInput.parse(novUplString)
                val istDate = dateFormatInput.parse(novIstString)

                val formattedDate1 = dateFormatOutput1.format(uplDate!!)
                val formattedDate2 = dateFormatOutput2.format(istDate!!)

                tvPickDate11.text = "$formattedDate1 - $formattedDate2"
            } catch (e: Exception) {
                e.printStackTrace()
                tvPickDate11.text = "Greška"
            }
        } else {
            tvPickDate11.text = "-"
        }

        tvPickDate11.setOnClickListener {
            DatePickerDialog(this, { _, year1, month1, day1 ->
                val firstDate = Calendar.getInstance()
                firstDate.set(year1, month1, day1)
                val NovUpl = firstDate.time

                DatePickerDialog(this, { _, year2, month2, day2 ->
                    val secondDate = Calendar.getInstance()
                    secondDate.set(year2, month2, day2)
                    val NovIst = secondDate.time

                    val formattedFebUpl = SimpleDateFormat("dd", Locale.getDefault()).format(NovUpl)
                    val formattedFebIst = SimpleDateFormat("dd.MM", Locale.getDefault()).format(NovIst)
                    tvPickDate11.text = "$formattedFebUpl - $formattedFebIst"

                    val success = dbHandler.updateDataUplata(
                        idClan,
                        JanUpl = null,
                        JanIst = null,
                        FebUpl = null, FebIst = null, MarUpl = null, MarIst = null,
                        AprUpl = null, AprIst = null, MajUpl = null, MajIst = null,
                        JunUpl = null, JunIst = null, JulUpl = null, JulIst = null,
                        AugUpl = null, AugIst = null, SepUpl = null, SepIst = null,
                        OktUpl = null, OktIst = null, NovUpl = NovUpl, NovIst = NovIst,
                        DecUpl = null, DecIst = null
                    )

                    if (success) {
                        Toast.makeText(this, "Podaci uspješno ažurirani", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Greška pri ažuriranju podataka", Toast.LENGTH_SHORT).show()
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val tvPickDate12 = findViewById<TextView>(R.id.DecDat)
        val decUplString = dbHandler.getDataUplata(idClan, "DecUpl")
        val decIstString = dbHandler.getDataUplata(idClan, "DecIst")

        if (decUplString != null && decIstString != null) {
            try {
                val uplDate = dateFormatInput.parse(decUplString)
                val istDate = dateFormatInput.parse(decIstString)

                val formattedDate1 = dateFormatOutput1.format(uplDate!!)
                val formattedDate2 = dateFormatOutput2.format(istDate!!)

                tvPickDate12.text = "$formattedDate1 - $formattedDate2"
            } catch (e: Exception) {
                e.printStackTrace()
                tvPickDate12.text = "Greška"
            }
        } else {
            tvPickDate12.text = "-"
        }

        tvPickDate12.setOnClickListener {
            DatePickerDialog(this, { _, year1, month1, day1 ->
                val firstDate = Calendar.getInstance()
                firstDate.set(year1, month1, day1)
                val DecUpl = firstDate.time

                DatePickerDialog(this, { _, year2, month2, day2 ->
                    val secondDate = Calendar.getInstance()
                    secondDate.set(year2, month2, day2)
                    val DecIst = secondDate.time

                    val formattedFebUpl = SimpleDateFormat("dd", Locale.getDefault()).format(DecUpl)
                    val formattedFebIst = SimpleDateFormat("dd.MM", Locale.getDefault()).format(DecIst)
                    tvPickDate12.text = "$formattedFebUpl - $formattedFebIst"

                    val success = dbHandler.updateDataUplata(
                        idClan,
                        JanUpl = null,
                        JanIst = null,
                        FebUpl = null, FebIst = null, MarUpl = null, MarIst = null,
                        AprUpl = null, AprIst = null, MajUpl = null, MajIst = null,
                        JunUpl = null, JunIst = null, JulUpl = null, JulIst = null,
                        AugUpl = null, AugIst = null, SepUpl = null, SepIst = null,
                        OktUpl = null, OktIst = null, NovUpl = null, NovIst = null,
                        DecUpl = DecUpl, DecIst = DecIst
                    )

                    if (success) {
                        Toast.makeText(this, "Podaci uspješno ažurirani", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Greška pri ažuriranju podataka", Toast.LENGTH_SHORT).show()
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        imageButton.setOnClickListener{val intent = Intent(this, Pocetna::class.java); startActivity(intent)}
        flbtn.setOnClickListener{
            val intent = Intent(this, Pocetna::class.java)
            startActivity(intent)
        }
        imageButton1.setOnClickListener{
            val intent = Intent(this, ListView::class.java)
            startActivity(intent)
        }
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
    }
}