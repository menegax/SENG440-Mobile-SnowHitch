package com.example.project1

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.JsonReader
import android.widget.ListView
import java.io.FileNotFoundException
import java.io.InputStreamReader


class HitchARideActivity : AppCompatActivity() {

    var rideList = arrayListOf<Ride>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hitch_aride)

        var listView = findViewById<ListView>(R.id.rides_list)
        // 1

        try {
            val file = openFileInput("rides.json")
            val reader = JsonReader(InputStreamReader(file))

            reader.beginArray()
            while (reader.hasNext()) {
                reader.beginObject()
                var name = ""
                var mountain = ""
                var availableSeats = ""
                while (reader.hasNext()) {
                    val key = reader.nextName()
                    when (key) {
                        "name" -> name = reader.nextString()
                        "mountain" -> mountain = reader.nextString()
                        "availableSeats" -> availableSeats = reader.nextString()
                    }
                }
                rideList.add(Ride(name, mountain, availableSeats))
                reader.endObject()
            }

            reader.close()
        } catch (e: FileNotFoundException) {
        }

        val arrayAdapter = RideAdapter(this, rideList)

        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedRide = rideList[position]

            val options = arrayOf("Map", "Email", "Text", "Call")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Connect how?")
            builder.setItems(options) { _, optionId ->
                dispatchAction(optionId, selectedRide)
            }
            builder.show()
        }
    }

    fun dispatchAction(optionId: Int, ride: Ride) {
        when (optionId) {
            0 -> TODO("Map")
            1 -> TODO("Email")
            2 -> TODO("Text")
            3 -> TODO("Call")
        }
    }
}
