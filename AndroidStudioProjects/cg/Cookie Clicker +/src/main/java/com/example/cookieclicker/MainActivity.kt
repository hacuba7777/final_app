package com.example.cookieclicker

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide

class MainActivity : Fragment() {
    val cookieData = MainActivity2.instance
    val upgradeRequestCode = 10
    val handler = Handler()
    lateinit var timer: Runnable
    var enableGadgets = true
    val secondInMillis = 1000L
    var gTimeout = 0
    companion object {
        fun newInstance() = MainActivity()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.activity_main, container, false)
        fun refreshCookieView() {
            val cookiesTextView: TextView = root.findViewById(R.id.cookieTextArea)
            println(cookieData)
            cookiesTextView.text = "🍪Cookies : ${cookieData.cookiesCounter}"
        }
        refreshCookieView()

        fun dice_roller(){
            val btn_show_popup = root.findViewById<Button>(R.id.btn_show_popup)
            btn_show_popup.setOnClickListener {
                val popup = PopupMenu(context, btn_show_popup)
                popup.inflate(R.menu.popup)
                popup.setOnMenuItemClickListener {
                    Toast.makeText(context, "Item : " + it.title, Toast.LENGTH_SHORT).show()
                    true
                }
                popup.show()
            }
        }
        dice_roller()
        fun showGIF(){

            val imageView:ImageView = root.findViewById(R.id.fireworks)
            Glide.with(this).load(R.drawable.firework).into(imageView)
            imageView.setVisibility(View.VISIBLE)
            object : CountDownTimer(3000, 3000) {
                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {
                    imageView.setVisibility(View.INVISIBLE)
                }
            }.start()

        }

        fun setupClickButton() {
            val button = root.findViewById<ImageButton>(R.id.cookieButton)
            val auto_click=root.findViewById<Button>(R.id.auto_click)
            auto_click.setOnClickListener {
                if (cookieData.cookiesCounter.toInt() % 50 == 0){
                    showGIF()
                }
                object : CountDownTimer(5000, 100) {
                    override fun onTick(millisUntilFinished: Long) {
                        cookieData.cookiesCounter += cookieData.clickValue
                        refreshCookieView()
                        println(cookieData.cookiesCounter)
                    }
                    override fun onFinish() {
                        // 計時器結束後要執行的程式碼
                    }
                }.start()
            }
            button.setOnClickListener {
                if (cookieData.cookiesCounter.toInt() % 50 == 0){
                    showGIF()
                }
                cookieData.cookiesCounter += cookieData.clickValue
                if (button.drawable == getResources().getDrawable(R.drawable.cookie_cry)) {
                    button.setImageDrawable(getResources().getDrawable(R.drawable.cookie))
                } else {
                    button.setImageDrawable(getResources().getDrawable(R.drawable.cookie_cry))
                }
                refreshCookieView()
                handler.postDelayed({
                    button.setImageResource(R.drawable.cookie)
                }, 800)
            }

        }
        setupClickButton()
        fun initTimer() {
        timer = Runnable {
            gTimeout += 1
                if(gTimeout > 80)
                {
                    enableGadgets = true
                    gTimeout = 0
                }
                if(cookieData.cookiesCounter > 8000)
                {
                   var rnds = (0..52).random()
                   if(rnds == 30 || rnds == 31 || rnds == 32 || rnds == 33 || rnds == 34 || rnds == 35 || rnds == 36 || rnds == 37 || rnds == 38 || rnds == 39 || rnds == 40)
                   {
                       refreshCookieView()
                       var button = root.findViewById<ImageButton>(R.id.cookieButton)
                       var background = root.findViewById<ConstraintLayout>(R.id.background)
                       cookieData.startBakeryBonus(handler , button, background)
                   }
                }
                cookieData.cookiesCounter += cookieData.cookiesPerSecond
                refreshCookieView()
                handler.postDelayed(timer, secondInMillis)
        }
        handler.postDelayed(timer, secondInMillis)
    }


        setupClickButton()
        initTimer()
        return root
    }
}


