package com.courtney.guesshomework

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    val secretNumber = SecretNumber()
    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setMessage(getString(R.string.Message))
                .setMessage(getString(R.string.restart))
                .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                    secretNumber.restart()
                    counter.setText(secretNumber.count.toString())
                    edt_number.setText("")
                    Log.d(TAG, "secret: ${secretNumber.secret}")
                }
                .setNeutralButton(getString(R.string.cancel), null)
                .show()
        }

        counter.setText(secretNumber.count.toString())

        Log.d(TAG, "secret: ${secretNumber.secret}")
    }

    fun check(view : View) {
        val n = edt_number.text.toString().toInt()
        println("n: $n")
        Log.d(TAG, "number: $n")

        val diff = secretNumber.validate(n)

        var message = when {
            diff < 0 -> getString(R.string.Higher)
            diff > 0 -> getString(R.string.Lower)
            diff == 0 && secretNumber.count < 3 -> "${getString(R.string.excellent)} ${secretNumber.secret}"
            else ->  getString(R.string.bingo)
        }

        counter.setText(secretNumber.count.toString())
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.Message))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()

    }
}
