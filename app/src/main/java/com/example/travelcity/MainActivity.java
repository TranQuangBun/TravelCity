package com.example.travelcity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.travelcity.HomeAdapter.FeaturedAdpater;
import com.example.travelcity.HomeAdapter.FeaturedHelperClass;
import com.example.travelcity.HomeAdapter.MostViewAdapter;
import com.example.travelcity.HomeAdapter.MostViewHelperClass;
import com.example.travelcity.LoginSignUp.LoginActivity;
import com.example.travelcity.UserDashBoard.ProfileActivity;
import com.example.travelcity.Map.MapLocation;
import com.example.travelcity.Map.TypeMap;
import com.example.travelcity.UserDashBoard.AllCategories;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    static final float END_SCALE = 0.7f;

    RecyclerView featuredRecycler, mostViewedRecycler;
    RecyclerView.Adapter adapter;

    //    take id from layout
    ImageView menuIcon;
    LinearLayout contentView;

    //    Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        featuredRecycler = findViewById(R.id.featured_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);

        //        Call id
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

//        Menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationDrawer();

        featureRecycler();
        mostViewedRecycler();
    }


    //calling ID and display in manyway (Banner Droper)
    private void navigationDrawer() {
        // Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNaviagationDrawer();
    }

    // Chia tỷ lệ View dựa trên độ lệch slide hiện tại ( di chuyển layout userDashBoard khi mở menu )
    private void animateNaviagationDrawer() {
//        set color when menu move out
//        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

//          Chia tỷ lệ View dựa trên độ lệch slide hiện tại
                final float diffScaleOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaleOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

//            Dịch khung nhìn, chiếm chiều rộng được chia tỷ lệ
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaleOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);

            }

        });
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void playGoogleMap(View view) {
        startActivity(new Intent(getApplicationContext(), MapLocation.class));
    }


    //Call trang thoong tin nguoi dung
    public void playUserInformation(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    //function chuyển hướng khi nhấn ( ở menu)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//1
        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;

            case R.id.nav_all_categories:
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
                break;

            case R.id.nav_add_missing_place:
                startActivity(new Intent(getApplicationContext(), TypeMap.class));
                break;
        }
        return true;

    }



    private void featureRecycler() {
        featuredRecycler.setHasFixedSize(true);

        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.restaurant, "Dali Restaurant", "Thực đơn đa dạng và phong phú, không gian sang trọng."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.jolibee, "Jollibee", "Thực đơn Jollibee đa dạng và phong phú, có rất nhiều sự lựa chọn cho bạn."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.bar, "Bar Clutch ", "Nơi bạn có thể giúp bạn giải tỏa căng thẳng, mệt mỏi "));


        adapter = new FeaturedAdpater(featuredLocations);
        featuredRecycler.setAdapter(adapter);

    }

    private void mostViewedRecycler() {
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewHelperClass> mostViewedLocations = new ArrayList<>();

        mostViewedLocations.add(new MostViewHelperClass(R.drawable.bar, "Bar Clutch ", "Nơi bạn có thể giúp bạn giải tỏa căng thẳng, mệt mỏi "));
        mostViewedLocations.add(new MostViewHelperClass(R.drawable.jolibee, "Jollibee's", "Thực đơn Jollibee đa dạng và phong phú, có rất nhiều sự lựa chọn cho bạn."));
        mostViewedLocations.add(new MostViewHelperClass(R.drawable.restaurant, "Dali Restaurant", "Thực đơn đa dạng và phong phú, không gian sang trọng."));

        adapter = new MostViewAdapter(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);

    }
}