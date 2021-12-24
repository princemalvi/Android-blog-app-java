package com.example.blogpages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements  NavigationBarView.OnItemSelectedListener{


    BottomNavigationView bottomNavigationView ;
    FrameLayout frameLayout;
    SharedPreferences sp ;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new HomeFragment());
        bottomNavigationView = findViewById(R.id.bottomNavBar);
        frameLayout = findViewById(R.id.frameLayout);
        sp = getSharedPreferences("loginPreference",0);
        Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
        bottomNavigationView.setOnItemSelectedListener(this);
    }
    public void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        switch (id){
            case R.id.myposts:
                intent = new Intent(MainActivity.this,MyBlogs.class);
                startActivity(intent);
                break;
            case R.id.logout:
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
        switch (id) {
            case R.id.Home:
                loadFragment(new HomeFragment());
                break;
            case R.id.Add:
                loadFragment(new AddBlogFragment());
                break;
            case R.id.Account:
                loadFragment(new AccountFragment());
                break;
        }
        return true;
    }

}