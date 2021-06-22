package com.henry.guessnumber

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*


class MaterialActivity : AppCompatActivity() {

    val secreteNumber = SecreteNumber()
    val TAG = MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))

        Log.d(TAG, "secrete : ${secreteNumber.secrete}")

        fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay Game")
                .setMessage("Are you sure ?")
                .setPositiveButton(getString(R.string.ok), { dialog,which ->
                    secreteNumber.reset()
                    Log.d(TAG, "secrete : ${secreteNumber.secrete}")
                    counter.setText(secreteNumber.count.toString())
                    number.setText("")
                })
                .setNeutralButton("Cancel",null)
                .show()
        }

        counter.setText(secreteNumber.count.toString())
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
        counter.setText(secreteNumber.count.toString())
//        Toast.makeText(this,meassge,Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.meassge))
            .setMessage(meassge)
            .setPositiveButton(getString(R.string.ok),null)
            .show()
    }

}