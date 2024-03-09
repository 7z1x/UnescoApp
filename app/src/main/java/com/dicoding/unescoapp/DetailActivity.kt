package com.dicoding.unescoapp

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Dapatkan data yang dikirimkan dari Intent
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val photo = intent.getIntExtra("photo", R.drawable.borobudur) // Mengambil foto default jika tidak ada data

        // Setel data ke tampilan di DetailActivity
        val imageView = findViewById<ImageView>(R.id.imageView)
        val textViewNama = findViewById<TextView>(R.id.textViewNama)
        val textViewDeskripsi = findViewById<TextView>(R.id.textViewDeskripsi)

        imageView.setImageResource(photo)
        textViewNama.text = name
        textViewDeskripsi.text = description

        // Pindahkan pernyataan log ke sini, setelah Anda mendapatkan nilai-nilai tersebut
        Log.d("DetailActivity", "Nama: $name, Deskripsi: $description, Photo: $photo")

        if (name != null && description != null) {
            // Set text untuk TextView
            textViewNama.text = name
            textViewDeskripsi.text = description
        } else {
            val errorMessage = "Data tidak tersedia" // Pesan kesalahan yang ingin Anda tampilkan
            textViewNama.text = errorMessage
            textViewDeskripsi.text = errorMessage
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        // Kembali ke aktivitas sebelumnya saat tombol "Kembali" di ActionBar ditekan
        onBackPressed()
        return true
    }
}
