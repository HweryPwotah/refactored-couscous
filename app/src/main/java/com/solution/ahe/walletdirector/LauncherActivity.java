package com.solution.ahe.walletdirector;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class LauncherActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Drawable formerDrawable;
    int formerItemId;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        setContentView(R.layout.activity_launcher);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getString(R.string.Dashboard_Snackbar), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_dashboard);

        MenuItem item = navigationView.getCheckedItem();
        assert item != null;
        changeColor2Selected(item.getIcon(), item.getItemId());
        displayScreen(R.id.nav_dashboard);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        changeColor2Selected(item.getIcon(), item.getItemId());
        displayScreen(item.getItemId());
        return true;
    }

    private void displayScreen(int id) {
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_dashboard: {
                fragment = new DashboardFragment();
                break;
            }
            case R.id.nav_record: {
                fragment = new RecordFragment();
                break;
            }
            case R.id.nav_report: {
                fragment = new ReportFragment();
                break;
            }
            case R.id.nav_toBePaid: {
                break;
            }
            case R.id.nav_debtLoan: {
                break;
            }
            case R.id.nav_budget: {
                break;
            }
            case R.id.nav_help: {
                break;
            }
            case R.id.nav_setting: {
                break;
            }
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void changeColor2Selected(Drawable drawable, int itemId) {
//        int color = ((ColorDrawable) drawable).getColor();
        //TODO find a way to change logo color
//        int color = ResourcesCompat.getColor(getResources(), itemId,null);
        changeObjectColor(drawable, R.color.colorPrimary);
        if (formerDrawable != null) {
            changeColor2Former(formerItemId);
//            DrawableCompat.setTint(formerDrawable.mutate(), ContextCompat.getColor(getApplicationContext(), formerColor));
        }
        formerDrawable = drawable;
        formerItemId = itemId;
    }

    private void changeColor2Former(int itemId) {
        switch (itemId) {
            case R.id.nav_dashboard: {
                changeObjectColor(formerDrawable, R.color.DrawerHome);
                break;
            }
            case R.id.nav_record: {
                changeObjectColor(formerDrawable, R.color.DrawerRecord);
                break;
            }
            case R.id.nav_report: {
                changeObjectColor(formerDrawable, R.color.DrawerReport);
                break;
            }
            case R.id.nav_toBePaid: {
                changeObjectColor(formerDrawable, R.color.DrawerToBePaid);
                break;
            }
            case R.id.nav_debtLoan: {
                changeObjectColor(formerDrawable, R.color.DrawerDebtAndLoan);
                break;
            }
            case R.id.nav_budget: {
                changeObjectColor(formerDrawable, R.color.DrawerBudget);
                break;
            }
            case R.id.nav_help: {
                changeObjectColor(formerDrawable, R.color.DrawerHelp);
                break;
            }
            case R.id.nav_setting: {
                changeObjectColor(formerDrawable, R.color.DrawerSettings);
                break;
            }
        }
    }

    private void changeObjectColor(Drawable drawable, int color) {
        DrawableCompat.setTint(drawable.mutate(), ContextCompat.getColor(getApplicationContext(), color));
    }
}
