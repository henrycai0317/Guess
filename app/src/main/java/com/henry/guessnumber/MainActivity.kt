package com.henry.guessnumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secreteNumber = SecreteNumber()
    val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "secrete : ${secreteNumber.secrete}")
    }
    fun check(view : View){
        val n = number.text.toString().toInt()
        println("number : $n")
        Log.d(TAG, "number : $n")

        val diff = secreteNumber.validate(n)
        var meassge = getString(R.string.yes_you_got_it)
        if(diff<0){
            meassge=getString(R.string.bigger)
        }else if(diff>0){
            meassge=getString(R.string.smaller)
        }
//        Toast.makeText(this,meassge,Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.meassge))
            .setMessage(meassge)
            .setPositiveButton(getString(R.string.ok),null)
            .show()
    }

}