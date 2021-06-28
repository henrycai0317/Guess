package com.henry.guessnumber

import android.content.DialogInterface
import android.content.Intent
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
    private  val REQUEST_RECORD = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        Log.d(TAG, "onCreate: ")
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))

        Log.d(TAG, "secrete : ${secreteNumber.secrete}")

        fab.setOnClickListener { view ->
            replay()
        }

        counter.setText(secreteNumber.count.toString())

        val count=getSharedPreferences("guess", MODE_PRIVATE)
            .getInt("REC_COUNTER",-1)
        val nick = getSharedPreferences("guess", MODE_PRIVATE)
            .getString("REC_NICKNAME",null)
        Log.d(TAG, "data :  $count / $nick ")


    }

    private fun replay() {
        AlertDialog.Builder(this)
                .setTitle("Replay Game")
                .setMessage("Are you sure ?")
                .setPositiveButton(getString(R.string.ok), { dialog, which ->
                    secreteNumber.reset()
                    Log.d(TAG, "secrete : ${secreteNumber.secrete}")
                    counter.setText(secreteNumber.count.toString())
                    number.setText("")
                })
                .setNeutralButton("Cancel", null)
                .show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
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
            .setPositiveButton(getString(R.string.ok),{dialog,which ->
                if(diff == 0 ){
                    val intent = Intent(this,RecordActivity::class.java)
                    intent.putExtra("COUNTER",secreteNumber.count)
                    startActivityForResult(intent,REQUEST_RECORD)
//                    startActivity(intent)

                }
            })
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_RECORD){
            if(resultCode == RESULT_OK){
                val nickname=data?.getStringExtra("NICK")
                Log.d(TAG, "onActivityResult: $nickname")
                replay()
            }
        }
    }
}