package com.example.project1

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.util.JsonWriter
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import java.io.*


class GiveARideActivity : AppCompatActivity() {

    private var rideList = arrayListOf<Ride>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_give_aride)

        supportActionBar?.title = "Give a Ride"
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
                var email = ""
                var cellphone = ""
                var comments = ""
                while (reader.hasNext()) {
                    val key = reader.nextName()
                    when (key) {
                        "name" -> name = reader.nextString()
                        "mountain" -> mountain = reader.nextString()
                        "availableSeats" -> availableSeats = reader.nextString()
                        "email" -> email = reader.nextString()
                        "cellphone" -> cellphone = reader.nextString()
                        "comments" -> comments = reader.nextString()
                    }
                }
                rideList.add(Ride(name, mountain, availableSeats, email, cellphone, comments))
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
        for (i in 0 until rideList.size) {
            var ride = rideList[i]
            writer.beginObject()
            writer.name("name").value(ride.name)
            writer.name("mountain").value(ride.mountain)
            writer.name("availableSeats").value(ride.availableSeats)
            writer.name("email").value(ride.email)
            writer.name("cellphone").value(ride.cellphone)
            writer.name("comments").value(ride.comments)
            writer.endObject()
        }
        writer.endArray()
        writer.close()
    }

    private fun checkRide(name: String, mountain: String, availableSeats: String, email: String, cellphone: String, comments: String): Boolean {
        var check = false

        if (name != "" && (mountain != "Mountain" && mountain != "Montagne") && availableSeats != "" && email != "" && cellphone != "") {
            rideList.add(Ride(name, mountain, availableSeats, email, cellphone, comments))
            check = true
        }
        return check
    }

    fun shareRide(view: View) {
        val name = findViewById<EditText>(R.id.nameText).text.toString()
        val mountain = findViewById<Spinner>(R.id.mountain).selectedItem.toString()
        val availableSeats = findViewById<EditText>(R.id.availableSeats).text.toString()
        val email = findViewById<EditText>(R.id.email).text.toString()
        val cellphone = findViewById<EditText>(R.id.cellphone).text.toString()
        val comments = findViewById<EditText>(R.id.comments).text.toString()
        val check = checkRide(name, mountain, availableSeats, email, cellphone, comments)
        if (check) {
            readRides()
            writeToFile()

            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)

            Toast.makeText(applicationContext, "You successfully shared your ride to $mountain", Toast.LENGTH_LONG)
                .show()
        } else {
            Toast.makeText(applicationContext, "Ride could not be shared; check fields", Toast.LENGTH_LONG)
                .show()
            val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
            view.startAnimation(bounceAnimation)
        }
    }
}