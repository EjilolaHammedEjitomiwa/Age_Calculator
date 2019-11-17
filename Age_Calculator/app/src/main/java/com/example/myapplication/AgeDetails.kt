package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_age_details.*
import kotlinx.android.synthetic.main.result_display_design.view.*
import java.util.*
import kotlin.collections.ArrayList

class AgeDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_details)
        val resultList = ArrayList<AgeDetailsModel>()

        //This get the current date date details
        val calendar = Calendar.getInstance()
        var currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val realMonth = currentMonth + 1
        var currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        //This get the user age details from the @MainActivity using intent extras
        val userYOB = intent.getStringExtra("userYear")
        val userMOB = intent.getStringExtra("userMonth")
        val userDOB = intent.getStringExtra("userDay")


        //User Age Details
        //User Spent year
        var spentYear = currentYear - userYOB.toInt()
        if (spentYear < 0) {
            spentYear = 0
        }

        //SPENT MONTH CALCULATIONS
        var spentMonth = 0

        if (spentYear <= 0) {
            spentMonth = currentMonth - userMOB.toInt()
            if (spentMonth <= 0) {
                spentMonth = userMOB.toInt() - currentMonth
            } else {
                val addToMonthPos = currentMonth - userDOB.toInt()
                val addToMonthNeg = userDOB.toInt() - currentMonth
                spentMonth = spentYear * 12
                spentMonth += if (addToMonthPos > 0) {
                    addToMonthPos
                }else{
                    addToMonthNeg
                }


            }
        }
        //SPENT DAYS CALCULATIONS
        var spentDays = 0
        if(spentYear<=0){

        }



        //
        resultList.add(AgeDetailsModel("Running Year", "$spentYear years"))
        resultList.add(AgeDetailsModel("Months spent", "$spentMonth Months"))
        resultList.add(AgeDetailsModel("User Day", userDOB))

        val myAdapter = AgeDetailsAdapter(this, resultList)
        list_view.adapter = myAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Hey!! I am sharing you this app because it is useful, it allows me to calculate my age easily click this " +
                            "link to get yours on Playstore \n https://play.google.com/store/apps/developer?id=Ejilola+Hammed+Ejitomiwa"
                )
                startActivity(Intent.createChooser(shareIntent, "Send to"))
                true
            }
            R.id.action_rate -> {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data =
                    Uri.parse("https://play.google.com/store/apps/developer?id=Ejilola+Hammed+Ejitomiwa")
                startActivity(openURL)
                return true
            }
            R.id.more_apps -> {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data =
                    Uri.parse("https://play.google.com/store/apps/developer?id=Ejilola+Hammed+Ejitomiwa")
                startActivity(openURL)
                return true
            }
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    inner class AgeDetailsModel(val title: String, val dataResult: String)

    inner class AgeDetailsAdapter(
        val context: Context,
        val resultList: ArrayList<AgeDetailsModel>
    ) : BaseAdapter() {
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val actualResult = resultList[p0]
            val myView = LayoutInflater.from(context).inflate(R.layout.result_display_design, null)
            myView.result_title.text = actualResult.title
            myView.result_data.text = actualResult.dataResult
            return myView
        }

        override fun getItem(p0: Int): Any {
            return p0
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return resultList.size
        }

    }

}
