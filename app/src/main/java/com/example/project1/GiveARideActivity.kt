package com.example.project1

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.util.JsonWriter
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.io.*


class GiveARideActivity : AppCompatActivity() {

    var rideList = arrayListOf<Ride>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_give_aride)
    }

    private fun readRides() {
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
    }

    private fun writeToFile() {
        val file = openFileOutput("rides.json", Context.MODE_PRIVATE)
        val writer = JsonWriter(OutputStreamWriter(file))
        writer.setIndent("  ")

        writer.beginArray()
        for (i in 0..rideList.size-1) {
            var ride = rideList[i]
            writer.beginObject()
            writer.name("name").value(ride.name)
            writer.name("mountain").value(ride.mountain)
            writer.name("availableSeats").value(ride.availableSeats)
            writer.endObject()
        }
        writer.endArray()
        writer.close()
    }

    fun shareRide(view: View) {
        readRides()

        var name = findViewById<EditText>(R.id.name).text.toString()
        var mountain = findViewById<EditText>(R.id.mountain).text.toString()
        var availableSeats = findViewById<EditText>(R.id.availableSeats).text.toString()

        rideList.add(Ride(name, mountain, availableSeats))

        writeToFile()

        val intent = Intent(this, MainActivity::class.java).apply {
        }

        startActivity(intent)

        Toast.makeText(applicationContext,"You successfully shared your ride to $mountain",Toast.LENGTH_LONG).show()
    }
}