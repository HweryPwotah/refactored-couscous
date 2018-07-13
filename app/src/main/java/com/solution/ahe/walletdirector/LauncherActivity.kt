package com.solution.ahe.walletdirector

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

class LauncherActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var formerDrawable: Drawable? = null
    private var formerItemId: Int = 0

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        //        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
        //            Drawable drawable = getResources().getDrawable(R.drawable.ic_drawer_home);
        //            drawable = DrawableCompat.wrap(drawable);
        //            DrawableCompat.setTint(drawable, Color.RED);
        //        }else{
        //            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_drawer_home);
        //            drawable = DrawableCompat.wrap(drawable);
        //            DrawableCompat.setTint(drawable, Color.RED);
        //        }

        setContentView(R.layout.activity_launcher)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { _ -> //view ->
            val intent = Intent(this, NewRecordActivity::class.java)
            startActivity(intent)
//            Snackbar.make(view, getString(R.string.Dashboard_Snackbar), Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.itemIconTintList = null

        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(R.id.nav_dashboard)

        val item = navigationView.checkedItem

        item!!.changeColor2Selected()
        displayScreen(R.id.nav_dashboard)
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        item.changeColor2Selected()
        displayScreen(item.itemId)
        return true
    }

    private fun displayScreen(id: Int) {
        var fragment: Fragment? = null

        when (id) {
            R.id.nav_dashboard -> {
                fragment = DashboardFragment()
            }
            R.id.nav_record -> {
                fragment = RecordFragment()
            }
            R.id.nav_report -> {
                fragment = ReportFragment()
            }
            R.id.nav_toBePaid -> {
            }
            R.id.nav_debtLoan -> {
            }
            R.id.nav_budget -> {
            }
            R.id.nav_help -> {
            }
            R.id.nav_setting -> {
            }
        }

        //replacing the fragment
        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.content_frame, fragment)
            ft.commit()
        }

        //other way to do it
        //        LayoutInflater inflater = getLayoutInflater();
        //        LinearLayout container = (LinearLayout) findViewById(R.id.content_frame);
        //        inflater.inflate(R.layout.activity_main, container);

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
    }

    private fun MenuItem.changeColor2Selected() {
        this.icon.changeObjectColor(R.color.colorPrimary)

        formerDrawable?.let{
            formerItemId.changeColor2Former()
        }

        formerDrawable = this.icon
        formerItemId = this.itemId
    }

    private fun Int.changeColor2Former() {
        when (this) {
            R.id.nav_dashboard -> {
                formerDrawable!!.changeObjectColor(R.color.DrawerHome)
            }
            R.id.nav_record -> {
                formerDrawable!!.changeObjectColor(R.color.DrawerRecord)
            }
            R.id.nav_report -> {
                formerDrawable!!.changeObjectColor(R.color.DrawerReport)
            }
            R.id.nav_toBePaid -> {
                formerDrawable!!.changeObjectColor(R.color.DrawerToBePaid)
            }
            R.id.nav_debtLoan -> {
                formerDrawable!!.changeObjectColor(R.color.DrawerDebtAndLoan)
            }
            R.id.nav_budget -> {
                formerDrawable!!.changeObjectColor(R.color.DrawerBudget)
            }
            R.id.nav_help -> {
                formerDrawable!!.changeObjectColor(R.color.DrawerHelp)
            }
            R.id.nav_setting -> {
                formerDrawable!!.changeObjectColor(R.color.DrawerSettings)
            }
        }
    }

    private fun Drawable.changeObjectColor(color: Int) {
        DrawableCompat.setTint(this.mutate(), ContextCompat.getColor(applicationContext, color))
    }
}
