package com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.R;

import static maes.tech.intentanim.CustomIntent.customType;

public class SponsorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        customType(this, "fadein-to-fadeout");


        YoYo.with(Techniques.RubberBand)
                .duration(900)
                .playOn(findViewById(R.id.textview1));
        YoYo.with(Techniques.RubberBand)
                .duration(900)
                .playOn(findViewById(R.id.textview2));
    }
    public void exit(View view) {
        finish();
    }
}
