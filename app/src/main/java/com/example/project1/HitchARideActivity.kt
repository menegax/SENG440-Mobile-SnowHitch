package com.example.project1

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.util.JsonReader
import android.view.Menu
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_hitch_aride.*
import java.io.FileNotFoundException
import java.io.InputStreamReader

class HitchARideActivity : AppCompatActivity() {

    lateinit var listView : ListView
    private var rideList = arrayListOf<Ride>()
    private var displayList = arrayListOf<Ride>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hitch_aride)
        supportActionBar?.title = getString(R.string.button1)
        listView = findViewById(R.id.rides_list) as ListView
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
                var ride = Ride(name, mountain, availableSeats, email, cellphone, comments, date)
                displayList.add(ride)
                rideList.add(ride)
                reader.endObject()
            }

            reader.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        val arrayAdapter = RideAdapter(this, displayList)
        listView.adapter = arrayAdapter


        arrayAdapter.notifyDataSetChanged()

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedRide = rideList[position]

            val intent = Intent(this, RideDetailsActivity::class.java).apply {
                putExtra("rideData", selectedRide)
            }
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.hitch, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        if(searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {
                        displayList.clear()

                        val search = newText.toLowerCase()
                        rideList.forEach {
                            if (it.mountain.toLowerCase().contains(search)) {
                                displayList.add(it)
                            }
                        }
                        createArrayAdapter()
                    } else {
                        displayList.clear()
                        displayList.addAll(rideList)
                        createArrayAdapter()
                    }
                    return true
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }
    private fun createArrayAdapter() {
        val arrayAdapter = RideAdapter(this, displayList)
        listView.adapter = arrayAdapter
    }
}



