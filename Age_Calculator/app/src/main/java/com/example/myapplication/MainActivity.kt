package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendar_icon.setOnClickListener {
            //This inflate the date picker layout
            val myView = LayoutInflater.from(this).inflate(R.layout.date_picker_layout, null)
            //DatePicker widget in the datepicker layout
            val cDate = myView.findViewById<DatePicker>(R.id.date_pick) as DatePicker
            cDate.setBackgroundColor(Color.BLUE)
            //Instance of the Calendar class
            val calendar: Calendar = Calendar.getInstance()

            //ALERT DIALOGUE BUILDER OPERATTONS
            val builder = AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle("Birthday date")
            //set message for alert dialog
            builder.setMessage("Please select your birthday date")
            //set icon for the alert dialogue
            builder.setIcon(R.mipmap.cal_icon)
            //performing positive action
            builder.setPositiveButton("Ok") { dialogInterface, which ->
                //month picked from DatePicker widget
                val userBirthMonth = cDate.month + 1
                //set values for the three editText (year, month and day)
                year_editText.setText(cDate.year.toString())
                month_editText.setText(userBirthMonth.toString())
                day_editText.setText(cDate.dayOfMonth.toString())
            }
            //performing negative action
            builder.setNegativeButton("Cancel") { dialogInterface, which ->
                Toast.makeText(applicationContext, "Canceled", Toast.LENGTH_LONG).show()
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setView(myView)
            alertDialog.setCancelable(true)
            alertDialog.show()
        }
    }

//create menu options
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }
//operations for menu selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            //Share intent
            R.id.action_share -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey!! I am sharing you this app because it is useful, it allows me to calculate my age easily click this " +
                            "link to get yours on Playstore \n https://play.google.com/store/apps/developer?id=Ejilola+Hammed+Ejitomiwa"
                )
                startActivity(Intent.createChooser(shareIntent, "Send to"))
                true
            }
            //Rate intent
            R.id.action_rate -> {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data =
                    Uri.parse("https://play.google.com/store/apps/developer?id=Ejilola+Hammed+Ejitomiwa")
                startActivity(openURL)
                return true
            }
            //More app intent
            R.id.more_apps -> {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data =
                    Uri.parse("https://play.google.com/store/apps/developer?id=Ejilola+Hammed+Ejitomiwa")
                startActivity(openURL)
                return true
            }
            //open the about activity
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    fun moveToAgeDetails(view: View) {
        //when clicked to change the background of the button text
        check_age_btn.setBackgroundResource(R.drawable.btn_background_stroke)

        //check if there is value in the three editText(Year,Month and Day)
        if (day_editText.length() == 0 || month_editText.length() == 0 || year_editText.length() == 0) {
            Toast.makeText(this, "Please enter your birthday date details", Toast.LENGTH_LONG)
                .show()
        } else {
            //Open the Age details activity with intent extras
            val intent = Intent(this, AgeDetails::class.java)
            intent.putExtra("userYear", year_editText.text.toString())
            intent.putExtra("userMonth", month_editText.text.toString())
            intent.putExtra("userDay", day_editText.text.toString())
            startActivity(intent)
        }
    }


}
