package com.example.project1

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.JsonReader
import android.widget.ListView
import java.io.FileNotFoundException
import java.io.InputStreamReader

class HitchARideActivity : AppCompatActivity() {

    private var rideList = arrayListOf<Ride>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hitch_aride)
        supportActionBar?.title = "Hitch a Ride"
        val listView = findViewById<ListView>(R.id.rides_list)
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
                var email = ""
                var cellphone = ""
                var comments = ""
                var date = ""
                while (reader.hasNext()) {
                    val key = reader.nextName()
                    when (key) {
                        "name" -> name = reader.nextString()
                        "mountain" -> mountain = reader.nextString()
                        "availableSeats" -> availableSeats = reader.nextString()
                        "email" -> email = reader.nextString()
                        "cellphone" -> cellphone = reader.nextString()
                        "comments" -> comments = reader.nextString()
                        "date" -> date = reader.nextString()
                    }
                }
                rideList.add(Ride(name, mountain, availableSeats, email, cellphone, comments, date))
                reader.endObject()
            }

            reader.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        val arrayAdapter = RideAdapter(this, rideList)

        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedRide = rideList[position]

            val intent = Intent(this, RideDetailsActivity::class.java).apply {
                putExtra("rideData", selectedRide)
            }
            startActivity(intent)
        }
    }
}
