package com.example.project1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.TextView

class RideDetailsActivity : AppCompatActivity() {

    var rideList = arrayListOf<Ride>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_details)

        val ride = intent.getSerializableExtra("rideData") as? Ride
        val mountain = ride?.mountain
        supportActionBar?.title = "Ride to $mountain"

        val nameText = findViewById<TextView>(R.id.name).apply {
            text = ride?.name
        }
        val availableSeatsText = findViewById<TextView>(R.id.availableSeats).apply {
            text = ride?.availableSeats
        }
    }

    fun contact(view: View){
        val ride = intent.getSerializableExtra("rideData") as? Ride
        val options = arrayOf("Email", "Text", "Call")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Connect how?")
        builder.setItems(options) { _, optionId ->
            dispatchAction(optionId, ride)
        }
        builder.show()
    }


    fun dispatchAction(optionId: Int, ride: Ride?) {
        when (optionId) {
            0 -> TODO("Email")
            1 -> TODO("Text")
            2 -> TODO("Call")
        }
    }
}
