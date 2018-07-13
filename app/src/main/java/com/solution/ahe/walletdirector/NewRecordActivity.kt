package com.solution.ahe.walletdirector

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class NewRecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_record)
        this.title = "New Record"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fab = findViewById<FloatingActionButton>(R.id.fabReceiptRecog)
        fab.setOnClickListener { view ->
            //TODO: intent to camera for receipt recognition
//            val intent = Intent(this, NewRecordActivity::class.java)
//            startActivity(intent)
            Snackbar.make(view, getString(R.string.New_Record_fab_Snackbar), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun done(){
        NavUtils.navigateUpFromSameTask(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.checklist, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        return if (id == R.id.action_done) {
            //TODO: validate record. finished? create record : return error
            Toast.makeText(this, "checklist is clicked",Toast.LENGTH_LONG).show()
            true
        } else super.onOptionsItemSelected(item)

    }
}
