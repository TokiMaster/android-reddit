package com.example.reddit_clone_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.reddit_clone_android.fragments.CommunityFragment;
import com.example.reddit_clone_android.fragments.FragmentTransition;
import com.example.reddit_clone_android.fragments.PostFragments;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                if(menuItem.getTitle().equals("Login")){
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }else if(menuItem.getTitle().equals("Register")){
                    startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                }else if (menuItem.getTitle().equals("Create post")){
                    startActivity(new Intent(MainActivity.this, CreatePostActivity.class));
                }else if (menuItem.getTitle().equals("Create community")){
                    startActivity(new Intent(MainActivity.this, CreateCommunityActivity.class));
                }else if (menuItem.getTitle().equals("Communities")){
                    FragmentTransition.to(CommunityFragment.newInstance(), MainActivity.this, false);
                }
                return true;
            }
        });
        FragmentTransition.to(PostFragments.newInstance(), this, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
