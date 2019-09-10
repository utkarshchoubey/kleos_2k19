package com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.activities;

import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.R;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.utils.UserPreferences;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import java.util.Random;

import am.appwise.components.ni.NoInternetDialog;

public class SplashScreen extends AwesomeSplash  {
    UserPreferences userPreferences;
    NoInternetDialog noInternetDialog ;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void initSplash(ConfigSplash configSplash) {
        userPreferences = new UserPreferences(this);

        configSplash.setBackgroundColor(R.color.colorPrimary);

        configSplash.setAnimCircularRevealDuration(1000);
        Random r = new Random();
        int Low = 10;
        int High = 100;
        int result = r.nextInt(High-Low) + Low;
        if(result %2 ==0 )configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);
        else configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);
        configSplash.setLogoSplash(R.drawable.splash); //or any other drawable
        configSplash.setAnimLogoSplashDuration(1500); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeIn);
        configSplash.setTitleSplash("");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(40f);
        configSplash.setAnimTitleDuration(1000);
        configSplash.setAnimTitleTechnique(Techniques.FadeIn);

    }

    @Override
    public void animationsFinished() {
            String a = userPreferences.getName();
            if(!userPreferences.getName().equals("")){
                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
                finish();
            }else{
                userPreferences.clearPrefs();
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
