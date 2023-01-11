package com.example.cookieclicker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class UpgradesActivity : Fragment(){
    val cookieData = MainActivity2.instance
    lateinit var autoClickerTextView: TextView
    lateinit var workersTextView: TextView
    lateinit var bakeriesTextView: TextView
    lateinit var upgradeClickTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.upgrade_activity, container, false)
        autoClickerTextView = root.findViewById(R.id.autoClickerText)
        workersTextView = root.findViewById(R.id.workersText)
        bakeriesTextView = root.findViewById(R.id.bakeriesText)
        upgradeClickTextView = root.findViewById(R.id.clickUpgradeText)



    fun refreshTextViews(){
        autoClickerTextView.text = "Cursors: ${cookieData.autoClickerUpgradeLevel}\n" + "Upgrade cost: ${cookieData.autoClickerPrice}"
        workersTextView.text = "Workers: ${cookieData.workersUpgradeLevel}\nWorker cost: ${cookieData.workersPrice}"
        bakeriesTextView.text = "Bakeries: ${cookieData.bakeriesUpgradeLevel}\nBakery cost: ${cookieData.bakeriesPrice}"
        upgradeClickTextView.text = "Click Upgrade: ${cookieData.clickUpgradeLevel}\nUpgrade cost: ${cookieData.getClickUpgPrice()}"
    }


    fun initClickUpgradeButton(){
        val clickUpgradeButton = root.findViewById<Button>(R.id.clickUpgradeButton)
        clickUpgradeButton.setOnClickListener{
            if( cookieData.cookiesCounter>= cookieData.getClickUpgPrice()){
                cookieData.cookiesCounter -= cookieData.getClickUpgPrice()
                cookieData.clickUpgradeLevel += 1
                cookieData.calculateClickValue()
                refreshTextViews()
            }else{
                Toast.makeText(context, "You cannot buy this upgrade!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun handleAutoClickerUpgrade() {
        root.findViewById<Button>(R.id.autoClickerButton).setOnClickListener {
            if (cookieData.cookiesCounter >= cookieData.autoClickerPrice) {
                cookieData.updateAutoClicker()
                refreshTextViews()
            } else {
                Toast.makeText(
                    context,
                    "You cannot buy this upgrade!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        root.findViewById<Button>(R.id.workersButton).setOnClickListener {
            if (cookieData.cookiesCounter >= cookieData.workersPrice) {
                cookieData.updateWorkers()
                refreshTextViews()
            } else {
                Toast.makeText(context, "You cannot buy this upgrade!", Toast.LENGTH_LONG).show()
            }
        }

        root.findViewById<Button>(R.id.bakeriesButton).setOnClickListener {
            if (cookieData.cookiesCounter >= cookieData.bakeriesPrice) {
                cookieData.updateBakeries()
                refreshTextViews()
            } else {
                Toast.makeText(context, "You cannot buy this upgrade!", Toast.LENGTH_LONG).show()
            }
        }

    }
        refreshTextViews()
        initClickUpgradeButton()
        handleAutoClickerUpgrade()
        return root}

}
