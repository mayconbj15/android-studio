package com.example.games;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /** inicializa uma fragment vazia na tela inicial **/
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(
                    R.id.fragment, new BlankFragment()).commit();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        Class fragmentClass = null;

        FragmentManager fragmentManager = getSupportFragmentManager();
        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        int id = menuItem.getItemId();

        if (id == R.id.AnimaisId) {
            fragmentClass = FragmentAnimais.class;
        } else if (id == R.id.AdivinhaId) {
            fragmentClass = FragmentAdivinha.class;
        }

        try{
            fragment = (Fragment) fragmentClass.newInstance();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }

        clearActivity();
        fragmentManager.beginTransaction().replace(
                R.id.fragment, fragment).commit();

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;


    }

    public void removeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().remove(fragment);
    }

    public void clearActivity(){
        TextView textView = findViewById(R.id.mainText);
        TextView textView2 = findViewById(R.id.messageText);
        ImageView imageView = findViewById(R.id.imageViewNerd);

        textView.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
    }
}
