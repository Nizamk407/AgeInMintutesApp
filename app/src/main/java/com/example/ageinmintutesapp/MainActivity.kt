package com.example.ageinmintutesapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var btndatepicker: Button
    private lateinit var tv_date_selected:TextView
    private lateinit var tv_date_mint:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btndatepicker = findViewById(R.id.btn_date)
        tv_date_selected =findViewById(R.id.tv_date)
        tv_date_mint =findViewById(R.id.tv_mint_count)

        btndatepicker.setOnClickListener {

            clickdatepicker()
        }
    }

    private fun clickdatepicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd =DatePickerDialog(this,
        {
                view,year,month,dayOfMonth ->

            val selecteddate = "$dayOfMonth/${month+1}/$year"
            tv_date_selected.setText(selecteddate)

            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate = sdf.parse(selecteddate)

            val selectedDateInMinutes = theDate.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMintutes = currentDate.time/60000

            val diffInMintues = currentDateInMintutes - selectedDateInMinutes

            tv_date_mint.text = diffInMintues.toString()

        },
            year,month,day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
            dpd.show()
    }
}