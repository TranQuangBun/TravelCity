package com.example.travelcity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelcity.LoginSignUp.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 4000;

    ImageView backgroundImage;
    TextView poweredByLine;

    //    Animations
    Animation sideAnim,bottomAnim;
    SharedPreferences onBoardingScreen;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        remove top status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

//        Hooks
        backgroundImage = findViewById(R.id.background_image);
        poweredByLine = findViewById(R.id.create_by_line);
//        Animations
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
//        set Animations on elements
        backgroundImage.setAnimation(sideAnim);
        poweredByLine.setAnimation(bottomAnim);

//        function time
        new Handler().postDelayed(() -> {

            onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();

        },SPLASH_TIMER);
    }
}