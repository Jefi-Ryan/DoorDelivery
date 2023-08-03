package com.example.doordelivery

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        R.drawable.steak

        val checkPizza = findViewById<CheckBox>(R.id.cbPizza)
        val checkSInd = findViewById<CheckBox>(R.id.cbSouthIndian)
        val checkSteak = findViewById<CheckBox>(R.id.cbSteak)
        val pricePizza = findViewById<TextView>(R.id.tvPizza)
        val priceSInd = findViewById<TextView>(R.id.tvSouthIndian)
        val priceSteak = findViewById<TextView>(R.id.tvSteak)
        val btnOrdered = findViewById<Button>(R.id.btnOrder)
        var pizza : Int = 0
        var SInd : Int = 0
        var steak : Int = 0
        var temp: Int
        var pizzaChanged : Boolean = false
        var SIndChanged : Boolean = false
        var steakChanged : Boolean = false
        Log.d("MyActivity","Pizza : ${pizzaChanged.toString()}")
        Log.d("MyActivity","SInd : ${SIndChanged.toString()}")
        Log.d("MyActivity","Steak : ${steakChanged.toString()}")

        btnOrdered.setOnClickListener {
            requestPermissions()
            val bundle = Bundle()
            Log.d("MainActivity","pizza : $pizza")
            Log.d("MainActivity","SInd : $SInd")
            Log.d("MainActivity","steak : $steak")

            bundle.putString("pizza",getprice(pricePizza))
            bundle.putString("SInd",getprice(priceSInd))
            bundle.putString("steak",getprice(priceSteak))
            Intent(this,BillActivity :: class.java).also{
                it.putExtras(bundle)
                startActivity(it)
            }
        }

        checkPizza.setOnClickListener{
            if (checkPizza.isChecked && !pizzaChanged) {
                var value: String = ""
                for (i in pricePizza.text) {
                    if (!i.equals('/')) {
                        value += i
                    }else{
                        break
                    }
                }
                pizza = value.toInt()
                temp = value.toInt() + 100
                pricePizza.text = temp.toString() + "/-"
                pizzaChanged = true
            }
            if(pizzaChanged && !checkPizza.isChecked){
                pricePizza.text = pizza.toString() + "/-"
                pizzaChanged = false
            }
        }

        checkSInd.setOnClickListener{
            if (checkSInd.isChecked && !SIndChanged) {
                var value: String = ""
                for (i in priceSInd.text) {
                    if (!i.equals('/')) {
                        value += i
                    }else{
                        break
                    }
                }
                SInd = value.toInt()


                temp = value.toInt() + 100
                priceSInd.text = temp.toString() + "/-"
                SIndChanged = true
            }
            if(SIndChanged && !checkSInd.isChecked) {
                priceSInd.text = SInd.toString() + "/-"
                SIndChanged = false
            }
        }

        checkSteak.setOnClickListener {
            if (checkSteak.isChecked && !steakChanged) {
                var value: String = ""
                for (i in priceSteak.text) {
                    if (!i.equals('/')) {
                        value += i
                    }else{
                        break
                    }
                }

                steak = value.toInt()


                temp = value.toInt() + 100
                priceSteak.text = temp.toString() + "/-"
                steakChanged = true
            }
            if(steakChanged && !checkSteak.isChecked) {
                priceSteak.text = steak.toString() + "/-"
                steakChanged = false
            }
        }
    }

    private fun getprice(element : TextView) : String?{
        var str = ""
        var index = element.text.indexOf('/')
        for (i in 0..index-1){
            str += element.text[i]
        }
        return str
    }
    private fun isaudioprovided() = ActivityCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) == 0

    private fun requestPermissions(){
        if(isaudioprovided()){
            return
        }
        Log.d("Permission","private fun requestPermissions called")
        val permissions = mutableListOf<String>()
        if(!isaudioprovided()){
            permissions.add(Manifest.permission.RECORD_AUDIO)
        }
        if(permissions.isNotEmpty()){
            Log.d("Permission","permissions to request : $permissions")
            ActivityCompat.requestPermissions(this,permissions.toTypedArray(),1)
            permissions.clear()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("permission","grantResults : ${grantResults.size}")
        Log.d("permission","permissions provided : ${permissions.size}")
        if(grantResults.isNotEmpty() && requestCode == 1){
            for(i in grantResults.indices){
                if(grantResults[i] == 1)
                    Log.d("Permission","${permissions[i]} granted")
            }
        }
    }
}