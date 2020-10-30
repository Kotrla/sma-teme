package com.example.lab4tema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity2 extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView navView;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment activeFragment;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        navView = findViewById(R.id.bnv_main_menu);
        navView.setOnNavigationItemSelectedListener(this);

        initializeViews();
        LoadFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragmentManager.beginTransaction().hide(activeFragment).show(fragment1).commit();
                activeFragment = fragment1;
                return true;

            case R.id.navigation_dashboard:
                fragmentManager.beginTransaction().hide(activeFragment).show(fragment2).commit();
                activeFragment = fragment2;
                return true;

            case R.id.navigation_notifications:
                fragmentManager.beginTransaction().hide(activeFragment).show(fragment3).commit();
                activeFragment = fragment3;
                return true;
        }
        return false;
    }
    private void LoadFragment() {
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, fragment3, "3").hide(fragment3).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, fragment2, "2").hide(fragment2).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, fragment1, "1").commit();
    }

    public void initializeViews() {
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        activeFragment = fragment1;
    }
}