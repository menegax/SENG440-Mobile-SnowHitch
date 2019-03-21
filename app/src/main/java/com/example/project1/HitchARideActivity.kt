package com.example.project1

import android.app.AlertDialog
import android.app.ListActivity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import java.time.LocalDate
import java.util.*

class HitchARideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hitch_aride)

        var listView = findViewById<ListView>(R.id.rides_list)
        // 1
        val rideList = rides

        val arrayAdapter = RideAdapter(this, rideList)

        listView.adapter = arrayAdapter

//        listView.setOnItemClickListener { _, _, position, _ ->
//            val selectedRide = rideList[position]
//
//            val options = arrayOf("Map", "Email", "Text", "Call")
//            val builder = AlertDialog.Builder(this)
//            builder.setTitle("Connect how?")
//            builder.setItems(options) { _, optionId ->
//                dispatchAction(optionId, selectedRide)
//            }
//            builder.show()
//        }

    }

    private val rides = listOf<Ride>(
        Ride("Joshua Meneghini", "joshuameneghini@gmail.com", "0273483623", "Mt Hutt", "2", "30-03-2019", "08:00", "Going early"),
        Ride("Jed O'Brien", "jedobrien@gmail.com", "0256987631", "Mt Ruapehu", "3", "30-03-2019", "07:00", "Going early"),
        Ride("Jess Eagan", "jesseagan@gmail.com", "0226987451", "Mt Coronet Peak", "2", "30-03-2019", "06:40", "Going early"),
        Ride("Adam Rundle", "adamrundle@gmail.com", "0215896434", "Mt Remarkables", "1", "30-03-2019", "07:25", "Going early")
    )

    fun dispatchAction(optionId: Int, ride: Ride) {
        when (optionId) {
            0 -> TODO("Map")
            1 -> TODO("Email")
            2 -> TODO("Text")
            3 -> TODO("Call")
        }
    }
}

