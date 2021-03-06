package com.henry.guessnumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
       val count= intent.getIntExtra("COUNTER",-1)
        record_counter.setText(count.toString())
        //OnClickLisener
        save.setOnClickListener { view ->
            val nick = nickName.text.toString()
            getSharedPreferences("guess", MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNTER",count)
                .putString("REC_NICKNAME",nick)
                .apply()
            val intent = Intent().putExtra("NICK",nick)
            setResult(RESULT_OK,intent)
            finish()
        }

    }
}