package com.example.corona.ViewController.Home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.corona.R;
import com.example.corona.ViewController.Home.FragmentHome.HomeFragment;
import com.example.corona.ViewController.Home.FragmentHome.QandAFrangment;
import com.example.corona.ViewController.Home.FragmentHome.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Shop");
        loadFragment(new HomeFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment= new HomeFragment();
            switch (item.getItemId()) {
                case R.id.action_home:
                    toolbar.setTitle("Shop");
                    fragment = new HomeFragment();
                    break;
                case R.id.action_q_and_a:
                    toolbar.setTitle("My Gifts");
                    fragment = new QandAFrangment();
                    break;
                case R.id.action_user:
                    toolbar.setTitle("Cart");
                    fragment = new UserFragment();
                    break;
            }
            loadFragment(fragment);
            return  true;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_contain, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
