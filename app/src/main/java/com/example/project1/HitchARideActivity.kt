package com.example.project1

import android.app.AlertDialog
import android.app.ListActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

class HitchARideActivity : ListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = ArrayAdapter<Friend>(this, android.R.layout.simple_list_item_1, friends)
    }

    private val friends = arrayOf<Friend>(
        Friend("Joshua Meneghini", "joshuameneghini@gmail.com", "0273483623", "12 Moorpark Place")
    )

    override fun onListItemClick(l: ListView?, v: View?, friendId: Int, id: Long) {
        val options = arrayOf("Map", "Email", "Text", "Call")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Connect how?")
        builder.setItems(options) { _, optionId ->
            dispatchAction(optionId, friends[friendId])
        }
        builder.show()
    }

    fun dispatchAction(optionId: Int, friend: Friend) {
        when (optionId) {
            0 -> TODO("Map")
            1 -> TODO("Email")
            2 -> TODO("Text")
            3 -> TODO("Call")
        }
    }
}

