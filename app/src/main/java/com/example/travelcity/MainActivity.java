package com.example.travelcity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
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
import com.example.travelcity.SaveProcessTravel.DetailActivity;
import com.example.travelcity.SaveProcessTravel.MainUpload;
import com.example.travelcity.SaveProcessTravel.UploadActivity;
import com.example.travelcity.UserDashBoard.EditProfileActivity;
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

            case R.id.nav_savetravel:
                startActivity(new Intent(getApplicationContext(), MainUpload.class));
                break;

            case R.id.type_map:
                startActivity(new Intent(getApplicationContext(), TypeMap.class));
                break;

            case R.id.nav_login:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;

            case R.id.nav_logout:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;

            case R.id.nav_hotel:
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com/travel/search?q=khach%20san%20o%20da%20nang&g2lb=2502548%2C2503771%2C2503781%2C4258168%2C4270442%2C4284970%2C4291517%2C4306835%2C4597339%2C4757164%2C4814050%2C4850738%2C4864715%2C4874190%2C4886480%2C4893075%2C4920132%2C4924070%2C4936396%2C4965990%2C4968087%2C4972345%2C4985711%2C4990494%2C4991446%2C72247955%2C72248281%2C72248644%2C72253158&hl=vi-VN&gl=vn&cs=1&ssta=1&ts=CAESCAoCCAMKAggDGhwSGhIUCgcI5w8QBRgKEgcI5w8QBRgLGAEyAhAAKgcKBToDVk5E&qs=CAE4Bg&ap=SABoAQ&ictx=1&ved=0CAAQ5JsGahcKEwiwiquV8OX-AhUAAAAAHQAAAAAQCw"));
                startActivity(intent);
                break;

            case R.id.nav_restaurant:
                Intent intent1=new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("https://www.google.com/search?tbs=lf:1,lf_ui:9&tbm=lcl&q=nha+hang+o+da+nang&rflfq=1&num=10&ved=2ahUKEwjjvJrQ8eX-AhXS6jgGHdkaDp4QtgN6BAhSEAg#rlfi=hd:;si:;mv:[[16.088883,108.25107589999999],[16.0424054,108.1528541]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!2m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:9"));
                startActivity(intent1);
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