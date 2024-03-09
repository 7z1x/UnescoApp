package com.dicoding.unescoapp

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        // Ambil data yang diterima dari Intent
        val nama = intent.getStringExtra("nama")
        val email = intent.getStringExtra("email")

        // Temukan TextView di layout
        val namaTextView = findViewById<TextView>(R.id.namaTextView)
        val emailTextView = findViewById<TextView>(R.id.emailTextView)

        // Periksa apakah data ada sebelum mengisi tampilan
        if (nama != null && email != null) {
            namaTextView.text = "$nama"
            emailTextView.text = "$email"
        } else {
            // Handle kasus di mana data tidak ada, misalnya dengan pesan kesalahan
            namaTextView.text = "Nama: Data Tidak Tersedia"
            emailTextView.text = "Email: Data Tidak Tersedia"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
