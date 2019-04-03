package com.example.project1

import android.content.Intent
import android.net.Uri
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
        val commentsText = findViewById<TextView>(R.id.comments).apply {
            text = ride?.comments
        }
        val dateText = findViewById<TextView>(R.id.date).apply {
            text = ride?.date
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
        val name = ride?.name
        val mountain = ride?.mountain
        val availableSeats = ride?.availableSeats
        val cellphone = ride?.cellphone
        val emailArray = arrayOf(ride?.email)
        val subject = "Ride share via SnowHitched"
        val text = "Hey, $name! I'd like to take your $availableSeats seats to $mountain, cheers!"
        when (optionId) {
            0 -> composeEmail(emailArray, subject, text)
            1 -> composeMmsMessage(text, cellphone)
            2 -> dialPhoneNumber(cellphone)
        }
    }

    fun composeEmail(addresses: Array<String?>, subject: String, text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_TEXT, text)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun dialPhoneNumber(phoneNumber: String?) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun composeMmsMessage(message: String, cellphone: String?) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto:$cellphone")  // This ensures only SMS apps respond
            putExtra("sms_body", message)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
