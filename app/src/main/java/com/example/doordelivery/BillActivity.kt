package com.example.doordelivery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BillActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill)
        val paybtn = findViewById<Button>(R.id.btnPay)
        val applybtn = findViewById<Button>(R.id.btnApply)
        val amount = findViewById<TextView>(R.id.tvAmount)
        val coupon = findViewById<EditText>(R.id.etCoupon)
        val name = findViewById<EditText>(R.id.etName)
        val number = findViewById<EditText>(R.id.etNumber)
        val cvv = findViewById<EditText>(R.id.etCvv)
        val bundle = intent.extras
        var applied = false
        var temp : Int = 0
        if(bundle != null) {
            temp = bundle.getString("pizza").toString().toInt()+
                    bundle.getString("SInd").toString().toInt()+
                    bundle.getString("steak").toString().toInt()
            Log.d("BillActivity","Bill : ${bundle.getString("pizza").toString()}")
            val temp = bundle.getString("pizza").toString().toInt() + bundle.getString("SInd").toString().toInt() +
                    bundle.getString("steak").toString().toInt()
            amount.text = temp.toString()
        }
        val original = temp

        applybtn.setOnClickListener{
            if(coupon.text.toString() == "jetjefi50" && !applied){
                amount.text = ((50 * amount.text.toString().toInt()) / 100).toString()
                Toast.makeText(this,"Coupon applied!", Toast.LENGTH_LONG).show()
                applied = true
            }
            else if(applied){
                Toast.makeText(this,"Coupon already applied!", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Invalid Coupon!", Toast.LENGTH_LONG).show()
                amount.text = original.toString()
            }
        }

        paybtn.setOnClickListener{
            if(name.text.length > 0 && number.text.length == 16 && cvv.text.length == 3){
                Intent(this,SuccessActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            }
            else{
                Toast.makeText(this,"Invalid Card details", Toast.LENGTH_LONG).show()
            }
        }
    }
}