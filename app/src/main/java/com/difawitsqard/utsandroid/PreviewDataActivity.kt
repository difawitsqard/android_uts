package com.difawitsqard.utsandroid

// Created : 05/06/24
// NIM     : 10121916
// Nama    : Difa Witsqa RD
// Kelas   : IF9K

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class PreviewDataActivity : AppCompatActivity() {

    private lateinit var jenisTesTextView: TextView
    private lateinit var tanggalKonfirmasiTextView: TextView
    private lateinit var nikTextView: TextView
    private lateinit var namaTextView: TextView
    private lateinit var tanggalLahirTextView: TextView
    private lateinit var jenisKelaminTextView: TextView
    private lateinit var hubunganTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_data)

        jenisTesTextView = findViewById(R.id.text_jenis_tes)
        tanggalKonfirmasiTextView = findViewById(R.id.text_tanggal_konfirmasi)
        nikTextView = findViewById(R.id.text_nik)
        namaTextView = findViewById(R.id.text_nama)
        tanggalLahirTextView = findViewById(R.id.text_tanggal_lahir)
        jenisKelaminTextView = findViewById(R.id.text_jenis_kelamin)
        hubunganTextView = findViewById(R.id.text_hubungan)

        // Get Data Intent
        val previewData = intent.getSerializableExtra("preview_data") as HashMap<String, String>

        // set TextViews
        jenisTesTextView.text = previewData["jenis_tes"]
        tanggalKonfirmasiTextView.text = previewData["tanggal_konfirmasi"]
        nikTextView.text = previewData["nik"]
        namaTextView.text = previewData["nama"]
        tanggalLahirTextView.text = previewData["tanggal_lahir"]
        jenisKelaminTextView.text = previewData["jenis_kelamin"]
        hubunganTextView.text = previewData["hubungan"]

        val btnUbah = findViewById<Button>(R.id.btn_ubah)
        btnUbah.setOnClickListener {
            finish() // Close & go ke halaman sebelumnya
        }

        val cekHasilButton = findViewById<Button>(R.id.btn_cek_hasil)
        cekHasilButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Berhasil Disimpan")
            builder.setMessage("Terima kasih, laporan Anda membantu kami dalam melakukan penanganan kasus secara tepat.")
            builder.setPositiveButton("Ok") { dialog, _ ->
                val intent = Intent(this, MainActivity::class.java)
                intent.action = "CLEAR_INPUT_FIELDS"
                startActivity(intent)


                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

    }
}