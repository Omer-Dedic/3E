package com.example.a3e

import ResetDatabaseWorker
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import androidx.work.*
import java.util.Calendar
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        scheduleDatabaseReset()

        val usernameInput: EditText = findViewById(R.id.editText)
        val passwordInput: EditText = findViewById(R.id.editTextTextPassword)
        val loginButton: Button = findViewById(R.id.button99)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username == "admin" && password == "admin123") {
                Toast.makeText(this, "Prijava uspješna!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Pocetna::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Pogrešno korisničko ime ili lozinka", Toast.LENGTH_SHORT)
                    .show()
                passwordInput.text.clear()
            }
        }
    }

    private fun scheduleDatabaseReset() {
        val currentDate = Calendar.getInstance()
        val nextReset = Calendar.getInstance()

        nextReset.set(Calendar.MONTH, Calendar.JANUARY)
        nextReset.set(Calendar.DAY_OF_MONTH, 1)
        nextReset.set(Calendar.HOUR_OF_DAY, 0)
        nextReset.set(Calendar.MINUTE, 0)
        nextReset.set(Calendar.SECOND, 0)
        nextReset.set(Calendar.MILLISECOND, 0)

        val delay = nextReset.timeInMillis - currentDate.timeInMillis
        if (delay <= 0) {
            nextReset.add(Calendar.YEAR, 1)
        }

        val workRequest = OneTimeWorkRequestBuilder<ResetDatabaseWorker>()
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(this).enqueueUniqueWork(
            "resetDatabase",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }
}
