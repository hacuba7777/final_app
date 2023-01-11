package com.example.cookieclicker

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.cookieclicker.R.id
import com.example.cookieclicker.R.layout


class MainActivity2 : AppCompatActivity() {
    val frgmanager=supportFragmentManager
    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    companion object {
        lateinit var instance: CookieData
            private set
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = CookieData()
        var cookieData=CookieData()
        setContentView(layout.activity_main2)

        frgmanager.beginTransaction()
            .replace(id.container,MainActivity())
            .commit()
        setButton()
        rankButton()
        val leave=findViewById<Button>(R.id.leave)
        leave.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle("----------------------")
                .setMessage("你要離開喔?")
                .setNegativeButton("不捨得離開"){ dialog,which->1
                }
                .setPositiveButton("忍痛離開"){ dialog,which->1
                    finish()
                }.show()
        }
        }

    fun rankButton() {
        val button = findViewById<Button>(R.id.rank)
        button.setOnClickListener {
            val intent = Intent(this, rank::class.java)
            intent.putExtra("cookieData", instance)
            startActivityForResult(intent,0)
        }
    }

    fun setButton() {
        val upbutton = findViewById<Button>(R.id.upgradeButton)
        val setbutton = findViewById<Button>(R.id.settingsButton)
        val clickbutton = findViewById<Button>(R.id.clickbutton)
        upbutton.setOnClickListener {
            upbutton.visibility= View.INVISIBLE
            setbutton.visibility=View.VISIBLE
            clickbutton.visibility=View.VISIBLE
            frgmanager.beginTransaction()
                .replace(id.container,UpgradesActivity())
                .commit()
        }
        setbutton.setOnClickListener {
            upbutton.visibility= View.VISIBLE
            setbutton.visibility=View.INVISIBLE
            clickbutton.visibility=View.VISIBLE
            frgmanager.beginTransaction()
                .replace(id.container,SettingsActivity())
                .commit()
        }
        clickbutton.setOnClickListener {
            upbutton.visibility= View.VISIBLE
            setbutton.visibility=View.VISIBLE
            clickbutton.visibility=View.INVISIBLE
            frgmanager.beginTransaction()
                .replace(id.container,MainActivity.newInstance())
                .commit()
        }
    }
}

