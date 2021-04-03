package com.olio.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity{
    private DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    HomeFragment fragmentHome;
    SettingsFragment fragmentSettings;
    private String text;
    Bundle args;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentHome = new HomeFragment();
        fragmentSettings = new SettingsFragment();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment frag = null;
                switch (item.getItemId()){
                    case R.id.nav_second_fragment:
                        frag = new SettingsFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_content, frag).commit();
                        item.setChecked(true);
                        navigationView.getMenu().getItem(0).setChecked(false);
                        break;
                    case R.id.nav_home:
                        frag = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_content, frag).commit();
                        item.setChecked(true);
                        navigationView.getMenu().getItem(1).setChecked(false);
                    default:
                        frag = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_content, frag).commit();
                }
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open,
                R.string.drawer_close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        if (savedInstanceState == null) {
            Fragment frag = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_content, frag).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}