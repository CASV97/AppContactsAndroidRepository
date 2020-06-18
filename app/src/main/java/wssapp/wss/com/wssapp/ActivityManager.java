package wssapp.wss.com.wssapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import wssapp.wss.com.wssapp.Fragments.ContactsFragment;
import wssapp.wss.com.wssapp.Fragments.NewContactFragment;
import wssapp.wss.com.wssapp.Fragments.WellcomeFragment;
import wssapp.wss.com.wssapp.Interfaces.IFragments;

public class ActivityManager extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,IFragments{
        public static final String USER="editTextUser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment wellFragment=null;
        wellFragment=new WellcomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main,wellFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_manager, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment contactsFragment=null;
        Fragment newContactsFragment=null;
        int selectedFragment = 0;
        if (id == R.id.nav_contacts) {
            contactsFragment=new ContactsFragment();
            selectedFragment=1;
        } else if (id == R.id.nav_newContact) {
            newContactsFragment=new NewContactFragment();
            selectedFragment=2;
        } else if (id == R.id.nav_share) {
            selectedFragment=3;
        } else if (id == R.id.nav_exit) {

        }
        if (selectedFragment == 1){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main,contactsFragment).commit();
        }else if(selectedFragment==2){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main,newContactsFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
