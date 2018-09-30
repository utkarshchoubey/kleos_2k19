package com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.mursaat.extendedtextview.AnimatedGradientTextView;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.R;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.adapters.SponsorsRecyclerAdapter;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.models.Sponsor;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.restapi.ApiBase;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.restapi.ApiEndpoints;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import am.appwise.components.ni.NoInternetDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class SponsorsActivity extends AppCompatActivity {
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        customType(this, "fadein-to-fadeout");
        AnimatedGradientTextView textView = findViewById(R.id.kleos);

        TextView textView1 = findViewById(R.id.textview1);
        TextView textView2 = findViewById(R.id.textview2);
        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        AVLoadingIndicatorView indicatorView = findViewById(R.id.avi);
        indicatorView.show();

        rv = (RecyclerView)findViewById(R.id.mainrv);
        rv.setVisibility(View.INVISIBLE);
        ApiEndpoints apiBase = ApiBase.getClient().create(ApiEndpoints.class);

        Call<List<Sponsor>> call = apiBase.getSponsors();
        call.enqueue(new Callback<List<Sponsor>>() {
            @Override
            public void onResponse(@NonNull Call<List<Sponsor>> call, @NonNull Response<List<Sponsor>> response) {
                indicatorView.hide();
                assert response.body() != null;
                if(response.body().size() >0){
                    textView1.setVisibility(View.GONE);
                    textView2.setVisibility(View.GONE);
                    rv.setVisibility(View.VISIBLE);
                    LinearLayoutManager layout = new LinearLayoutManager(SponsorsActivity.this,LinearLayoutManager.VERTICAL,false);
                    rv.setLayoutManager(layout);
                    rv.setAdapter(new SponsorsRecyclerAdapter(SponsorsActivity.this,response.body()));
                }else{
                    textView1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Sponsor>> call, @NonNull Throwable t) {
                NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(SponsorsActivity.this).build();
            }
        });

    }
    public void exit(View view) {
        finish();
    }
}
