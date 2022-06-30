package com.example.reddit_clone_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.reddit_clone_android.fragments.CommunitiesPostsFragment;
import com.example.reddit_clone_android.fragments.CommunityFragment;
import com.example.reddit_clone_android.fragments.FragmentTransition;
import com.example.reddit_clone_android.fragments.PostFragments;
import com.example.reddit_clone_android.http.BackendHttpRequests;
import com.example.reddit_clone_android.model.CommunityDTO;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community);
        Intent intent = getIntent();
        Long id = intent.getLongExtra("id", 0);
        Call<CommunityDTO> communityDTOCall = BackendHttpRequests.getInstance().getCommunityService().findCommunity(id);
        communityDTOCall.enqueue(new Callback<CommunityDTO>() {
            @Override
            public void onResponse(Call<CommunityDTO> call, Response<CommunityDTO> response) {
                if(response.isSuccessful()){
                    TextView textView = findViewById(R.id.communityName);
                    CommunityDTO communityDTO = response.body();
                    textView.setText(communityDTO.getName());
                    FragmentTransition.to(CommunitiesPostsFragment.newInstance(), SecondActivity.this, false);
                }
            }

            @Override
            public void onFailure(Call<CommunityDTO> call, Throwable t) {

            }
        });
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
                mDrawerLayout.closeDrawers();
                if(menuItem.getTitle().equals("Home")){
                    FragmentTransition.to(PostFragments.newInstance(), SecondActivity.this, false);
                }else if(menuItem.getTitle().equals("Login")){
                    startActivity(new Intent(SecondActivity.this, LoginActivity.class));
                }else if(menuItem.getTitle().equals("Register")){
                    startActivity(new Intent(SecondActivity.this, RegisterActivity.class));
                }else if(menuItem.getTitle().equals("Create post")){
                    startActivity(new Intent(SecondActivity.this, CreatePostActivity.class));
                }else if(menuItem.getTitle().equals("Create community")){
                    startActivity(new Intent(SecondActivity.this, CreateCommunityActivity.class));
                }else if(menuItem.getTitle().equals("Communities")){
                    FragmentTransition.to(CommunityFragment.newInstance(), SecondActivity.this, false);
                }
                return true;
            }
        });
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
