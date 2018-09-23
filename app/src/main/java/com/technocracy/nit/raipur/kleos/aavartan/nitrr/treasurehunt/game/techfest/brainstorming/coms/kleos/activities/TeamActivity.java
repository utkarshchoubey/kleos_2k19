package com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.R;

import static maes.tech.intentanim.CustomIntent.customType;

public class TeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        customType(this, "fadein-to-fadeout");
    }

    public void exit(View view) {
        finish();
    }
}
