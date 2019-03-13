package com.example.project1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun hitchARide(view: View){
        val intent = Intent(this, HitchARideActivity::class.java).apply {
        }
        startActivity(intent)
    }

    fun giveARide(view: View){
        val intent = Intent(this, GiveARideActivity::class.java).apply {
        }
        startActivity(intent)
    }
}
