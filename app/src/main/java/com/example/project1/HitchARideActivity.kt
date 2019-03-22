package com.example.project1

import android.app.AlertDialog
import android.app.ListActivity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import java.time.LocalDate
import java.util.*

class HitchARideActivity : AppCompatActivity() {

    var rideList = arrayListOf<Ride>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hitch_aride)

        var listView = findViewById<ListView>(R.id.rides_list)
        // 1
        //val rideList = rides
        getRidesFromJSON()

        val arrayAdapter = RideAdapter(this, rideList)

        listView.adapter = arrayAdapter

    }

    fun getRidesFromJSON() {
        var json : String? = null
        try {
            val inputStream: InputStream = assets.open("rides.json")
            json = inputStream.bufferedReader().use { it.readText() }

            var jsonArray = JSONArray(json)

            for (i in 0 until jsonArray.length()) {
                var jsonObject = jsonArray.getJSONObject(i)
                rideList.add(Ride(jsonObject.getString("name"), jsonObject.getString("mountain"), jsonObject.getString("availableSeats")))
            }
        } catch (e : IOException) {

        }
    }

//    private val rides = listOf<Ride>(
//        Ride("Joshua Meneghini", "joshuameneghini@gmail.com", "0273483623", "Mt Hutt", "2", "30-03-2019", "08:00", "Going early"),
//        Ride("Jed O'Brien", "jedobrien@gmail.com", "0256987631", "Mt Ruapehu", "3", "30-03-2019", "07:00", "Going early"),
//        Ride("Jess Eagan", "jesseagan@gmail.com", "0226987451", "Mt Coronet Peak", "2", "30-03-2019", "06:40", "Going early"),
//        Ride("Adam Rundle", "adamrundle@gmail.com", "0215896434", "Mt Remarkables", "1", "30-03-2019", "07:25", "Going early")
//    )

    fun dispatchAction(optionId: Int, ride: Ride) {
        when (optionId) {
            0 -> TODO("Map")
            1 -> TODO("Email")
            2 -> TODO("Text")
            3 -> TODO("Call")
        }
    }
}

