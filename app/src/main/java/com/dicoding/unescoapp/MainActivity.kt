package com.dicoding.unescoapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvSitus: RecyclerView
    private val list = ArrayList<Situs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvSitus = findViewById(R.id.rv_situs)
        rvSitus.setHasFixedSize(true)
        list.addAll(getListSitus())
        showRecyclerList()

        // Definisikan data yang akan digunakan
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        // Set listener untuk item yang diklik di dalam ListView
        val listSitusAdapter = ListSitusAdapter(list)
        listSitusAdapter.setOnItemClickCallback(object : ListSitusAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Situs) {
                // Dapatkan data yang dikirimkan dari Intent
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("name", data.name)
                intent.putExtra("description", data.description)
                intent.putExtra("photo", data.photo)
                startActivity(intent)
            }
        })

        rvSitus.adapter = listSitusAdapter


        // Jangan lupa untuk melepaskan dataPhoto setelah selesai menggunakannya
        dataPhoto.recycle()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_detail -> {
                val detailIntent = Intent(this, AboutActivity::class.java)
                // Mengirim data yang diperlukan ke AboutActivity (halaman detail)
                detailIntent.putExtra("nama", "ZULFAHMI M. ARDIANTO")
                detailIntent.putExtra("email", "a548bky4459@bangkit.academy")
                // Mulai aktivitas AboutActivity (halaman detail)
                startActivity(detailIntent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListSitus(): ArrayList<Situs> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listSitus = ArrayList<Situs>()
        for (i in dataName.indices) {
            val situs = Situs(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listSitus.add(situs)
        }
        dataPhoto.recycle() // Memanggil recycle() untuk menghindari memory leak
        return listSitus
    }

    private fun showRecyclerList() {
        rvSitus.layoutManager = LinearLayoutManager(this)
        val listSitusAdapter = ListSitusAdapter(list)
        rvSitus.adapter = listSitusAdapter

        listSitusAdapter.setOnItemClickCallback(object : ListSitusAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Situs) {
                showSelectedSitus(data)
            }
        })
    }

    private fun showSelectedSitus(situs: Situs) {
        Toast.makeText(this, "Kamu memilih " + situs.name, Toast.LENGTH_SHORT).show()
    }
}