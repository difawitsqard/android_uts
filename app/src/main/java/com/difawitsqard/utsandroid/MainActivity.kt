package com.difawitsqard.utsandroid

// Created : 05/06/24
// NIM     : 10121916
// Nama    : Difa Witsqa RD
// Kelas   : IF9K

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var jenisTesRadioGroup: RadioGroup
    private lateinit var tanggalKonfirmasiInput: TextInputEditText
    private lateinit var nikInput: TextInputEditText
    private lateinit var namaInput: TextInputEditText
    private lateinit var tanggalLahirInput: TextInputEditText
    private lateinit var jenisKelaminRadioGroup: RadioGroup
    private lateinit var hubunganRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jenisTesRadioGroup = findViewById(R.id.radio_jenis_tes)
        tanggalKonfirmasiInput = findViewById(R.id.input_tgl_konfirmasi)
        nikInput = findViewById(R.id.input_nik)
        namaInput = findViewById(R.id.input_name)
        tanggalLahirInput = findViewById(R.id.input_tgl_lahir)
        jenisKelaminRadioGroup = findViewById(R.id.radio_jenis_kelamin)
        hubunganRadioGroup = findViewById(R.id.radio_hubungan)

        val selanjutnyaButton = findViewById<Button>(R.id.btn_selanjutnya)
        selanjutnyaButton.setOnClickListener {
            if (validateInput()) {
                if (findViewById<CheckBox>(R.id.checkbox_setuju).isChecked) {
                    // Get  Jenis Tes
                    val selectedJenisTesId = jenisTesRadioGroup.checkedRadioButtonId
                    val selectedJenisTes =
                        findViewById<RadioButton>(selectedJenisTesId).text.toString()

                    // Get TextInputs
                    val tanggalKonfirmasi = tanggalKonfirmasiInput.text.toString()
                    val nik = nikInput.text.toString()
                    val nama = namaInput.text.toString()
                    val tanggalLahir = tanggalLahirInput.text.toString()

                    // Get Jenis Kelamin
                    val selectedJenisKelaminId = jenisKelaminRadioGroup.checkedRadioButtonId
                    val selectedJenisKelamin =
                        findViewById<RadioButton>(selectedJenisKelaminId).text.toString()

                    // Get Hubungan
                    val selectedHubunganId = hubunganRadioGroup.checkedRadioButtonId
                    val selectedHubungan =
                        findViewById<RadioButton>(selectedHubunganId).text.toString()

                    // data
                    val previewData = hashMapOf(
                        "jenis_tes" to selectedJenisTes,
                        "tanggal_konfirmasi" to tanggalKonfirmasi,
                        "nik" to nik,
                        "nama" to nama,
                        "tanggal_lahir" to tanggalLahir,
                        "jenis_kelamin" to selectedJenisKelamin,
                        "hubungan" to selectedHubungan
                    )

                    // start PreviewDataActivity
                    val intent = Intent(this, PreviewDataActivity::class.java)
                    intent.putExtra("preview_data", previewData)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Persetujuan anda diperlukan", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Harap isi semua data yang diperlukan", Toast.LENGTH_SHORT).show()
            }
        }

        if (intent.action == "CLEAR_INPUT_FIELDS") {
            clearInputFields()
        }
    }

    private fun validateInput(): Boolean {
        val hasEmptyField = jenisTesRadioGroup.checkedRadioButtonId == -1 ||
                tanggalKonfirmasiInput.text.isNullOrBlank() ||
                nikInput.text.isNullOrBlank() ||
                namaInput.text.isNullOrBlank() ||
                tanggalLahirInput.text.isNullOrBlank() ||
                jenisKelaminRadioGroup.checkedRadioButtonId == -1 ||
                hubunganRadioGroup.checkedRadioButtonId == -1
        return !hasEmptyField
    }

    private fun clearInputFields() {
        jenisTesRadioGroup.clearCheck()
        tanggalKonfirmasiInput.text = null
        nikInput.text = null
        namaInput.text = null
        tanggalLahirInput.text = null
        jenisKelaminRadioGroup.clearCheck()
        hubunganRadioGroup.clearCheck()
    }

}

