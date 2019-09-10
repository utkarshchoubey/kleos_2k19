package com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.florent37.viewtooltip.ViewTooltip;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.R;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.models.Question;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.models.User;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.restapi.ApiBase;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.restapi.ApiEndpoints;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.utils.UserPreferences;
import com.viven.imagezoom.ImageZoomHelper;
import am.appwise.components.ni.NoInternetDialog;
import es.dmoral.toasty.Toasty;
import io.github.mthli.slice.Slice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class QuestionActivity extends YouTubeBaseActivity {
    TextView tv, tv1, textView1, textView2, textView3, textView4, textView5;
    UserPreferences userPreferences;
    EditText ed;
    TextInputLayout til;
    ApiEndpoints apiBase;
    ImageZoomHelper imageZoomHelper;
    Button button, dialer;
    Question q;
    YouTubePlayerView videoView;
    ImageView img, img2;
    Toolbar toolbar;
    int pos;
    private static final String BASE_URL = "http://aavartan.nitrr.ac.in:8080";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        customType(this, "fadein-to-fadeout");
        userPreferences = new UserPreferences(this);

        img = (ImageView) findViewById(R.id.img);
        img2 = (ImageView) findViewById(R.id.img2);
        imageZoomHelper = new ImageZoomHelper(this);
        ImageZoomHelper.setViewZoomable(findViewById(R.id.img));
        ImageZoomHelper.setViewZoomable(findViewById(R.id.img2));
        videoView = (YouTubePlayerView) findViewById(R.id.video);
        tv = (TextView) findViewById(R.id.questionText);
        tv1 = (TextView) findViewById(R.id.kleos);
        til = (TextInputLayout) findViewById(R.id.til);
        ed = (EditText) findViewById(R.id.answer);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ques1);
        button = (Button) findViewById(R.id.submitB);
        dialer = (Button) findViewById(R.id.dialer);
        Slice slice = new Slice(button);
        slice.setRadius(8f);
        slice.setColor(Color.parseColor("#00BB84"));
        Slice slice2 = new Slice(dialer);
        slice2.setRadius(8f);
        slice2.setColor(Color.parseColor("#00BB84"));

        textView1 = (TextView) findViewById(R.id.t1);
        textView2 = (TextView) findViewById(R.id.t2);
        textView3 = (TextView) findViewById(R.id.t3);
        textView4 = (TextView) findViewById(R.id.t4);
        textView5 = (TextView) findViewById(R.id.t5);
        toolbar=(Toolbar)findViewById(R.id.toolbarAnswer);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        q = getIntent().getParcelableExtra("question");
        pos = getIntent().getExtras().getInt("id");
        q.image=BASE_URL+q.image;
        Log.d("ImageUrlQuestions",""+q.image);
        switch (pos) {
            case 1:
                videoView.setVisibility(View.GONE);
                videoView.setEnabled(false);
                linearLayout.setVisibility(View.GONE);
                linearLayout.setEnabled(false);
                img.setVisibility(View.GONE);
                img.setEnabled(false);
                img2.setVisibility(View.GONE);
                img2.setEnabled(false);
                dialer.setVisibility(View.GONE);
                dialer.setEnabled(false);
                break;
            case 2:
                videoView.setVisibility(View.GONE);
                videoView.setEnabled(false);
                img.setVisibility(View.GONE);
                img.setEnabled(false);
                linearLayout.setVisibility(View.GONE);
                linearLayout.setEnabled(false);
                img2.setVisibility(View.GONE);
                img2.setEnabled(false);
                dialer.setVisibility(View.GONE);
                dialer.setEnabled(false);
                break;
            case 3:
                videoView.setVisibility(View.GONE);
                videoView.setEnabled(false);
//                img.setImageResource(R.drawable.q3a);
                Picasso.get().load(q.image).placeholder(R.drawable.temp_photo).into(img);
                //img2.setImageResource(R.drawable.q3b);
                linearLayout.setVisibility(View.GONE);
                img2.setVisibility(View.GONE);
                img2.setEnabled(false);
                linearLayout.setEnabled(false);
                dialer.setVisibility(View.GONE);
                dialer.setEnabled(false);
                break;
            case 4:
                videoView.setVisibility(View.GONE);
                videoView.setEnabled(false);
//                img.setImageResource(R.drawable.q4);
              //  Picasso.get().load(q.image).placeholder(R.drawable.temp_photo).into(img);
                linearLayout.setVisibility(View.GONE);
                linearLayout.setEnabled(false);
                img.setVisibility(View.GONE);
                img.setEnabled(false);
                img2.setVisibility(View.GONE);
                img2.setEnabled(false);
                dialer.setVisibility(View.GONE);
                dialer.setEnabled(false);
                break;
            case 5:
                videoView.setVisibility(View.GONE);
                videoView.setEnabled(false);
//                img.setImageResource(R.drawable.q5);
              //  Picasso.get().load(q.image).placeholder(R.drawable.temp_photo).into(img);

                img.setVisibility(View.GONE);
                img.setEnabled(false);
                img2.setVisibility(View.GONE);
                img2.setEnabled(false);
                linearLayout.setVisibility(View.GONE);
                linearLayout.setEnabled(false);
                dialer.setVisibility(View.GONE);
                dialer.setEnabled(false);
                break;
            case 6:
                videoView.setVisibility(View.GONE);
                videoView.setEnabled(false);
//                img.setImageResource(R.drawable.q6);
               // Picasso.get().load(q.image).placeholder(R.drawable.temp_photo).into(img);
                linearLayout.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
                img.setEnabled(false);
                linearLayout.setEnabled(false);
                img2.setVisibility(View.GONE);
                img2.setEnabled(false);
                dialer.setVisibility(View.GONE);
                dialer.setEnabled(false);
                break;
            case 7:
                videoView.setVisibility(View.GONE);
                videoView.setEnabled(false);
//                img.setImageResource(R.drawable.q7);
               // Picasso.get().load(q.image).placeholder(R.drawable.temp_photo).into(img);
                img.setVisibility(View.GONE);
                img.setEnabled(false);
                linearLayout.setVisibility(View.GONE);
                linearLayout.setEnabled(false);
                img2.setVisibility(View.GONE);
                img2.setEnabled(false);
                dialer.setVisibility(View.GONE);
                dialer.setEnabled(false);
                break;
            case 8:
                Picasso.get().load(q.image).placeholder(R.drawable.temp_photo).into(img);
                linearLayout.setVisibility(View.GONE);
                linearLayout.setEnabled(false);
                img2.setVisibility(View.GONE);
                img2.setEnabled(false);
                dialer.setVisibility(View.GONE);
                dialer.setEnabled(false);
                videoView.setVisibility(View.GONE);
                videoView.setEnabled(false);
                break;
            case 9:
                Picasso.get().load(q.image).placeholder(R.drawable.temp_photo).into(img);
                linearLayout.setVisibility(View.GONE);
                linearLayout.setEnabled(false);
                img2.setVisibility(View.GONE);
                img2.setEnabled(false);
                dialer.setVisibility(View.GONE);
                dialer.setEnabled(false);
                videoView.setVisibility(View.GONE);
                videoView.setEnabled(false);
                break;
            case 10:
                videoView.setVisibility(View.GONE);
                videoView.setEnabled(false);
//                img.setImageResource(R.drawable.q10);
               // Picasso.get().load(q.image).placeholder(R.drawable.temp_photo).into(img);
                linearLayout.setVisibility(View.GONE);
                linearLayout.setEnabled(false);
                img2.setVisibility(View.GONE);
                img2.setEnabled(false);
                dialer.setVisibility(View.GONE);
                dialer.setEnabled(false);
                break;
            case 11:
                videoView.setVisibility(View.GONE);
                videoView.setEnabled(false);
//                img.setImageResource(R.drawable.box);
                img.setVisibility(View.GONE);
                img.setEnabled(false);
                linearLayout.setVisibility(View.GONE);
                linearLayout.setEnabled(false);
                img2.setVisibility(View.GONE);
                img2.setEnabled(false);
                dialer.setVisibility(View.GONE);
                dialer.setEnabled(false);
                break;
            case 12:
                img.setVisibility(View.GONE);
                img.setEnabled(false);
                videoView.setVisibility(View.GONE);
                videoView.setEnabled(false);
                linearLayout.setVisibility(View.GONE);
                linearLayout.setEnabled(false);
                img2.setVisibility(View.GONE);
                img2.setEnabled(false);
                dialer.setVisibility(View.GONE);
                dialer.setEnabled(false);
                break;
        }

        tv1.setText(q.title);
        tv.setText(q.question);

        int level = Integer.parseInt(userPreferences.getLevel());

        if (pos <= level) {
            button.setVisibility(View.INVISIBLE);
            ed.setVisibility(View.INVISIBLE);
            til.setVisibility(View.INVISIBLE);
            til.setEnabled(false);
            button.setEnabled(false);
            ed.setEnabled(false);
        }

        apiBase = ApiBase.getClient().create(ApiEndpoints.class);
    }

    Handler h2 = new Handler();
    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            switch (pos) {
                case 1:
                    int curBrightnessValue = android.provider.Settings.System.getInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, -1);
                    if (curBrightnessValue < 130) {
                        textView1.setText("G");
                        textView2.setText("");
                        textView3.setText("B");
                        textView4.setText("");
                        textView5.setText("E");
                    } else {
                        textView1.setText("");
                        textView2.setText("C");
                        textView3.setText("");
                        textView4.setText("F");
                        textView5.setText("");
                    }
                    break;
                default:
                    h2.removeCallbacks(r2);
            }
            h2.postDelayed(r2, 500);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        h2.removeCallbacks(r2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        h2.postDelayed(r2, 500);
    }

    public void backToHomeActivity(View view) {
        finish();
    }

    public void submitAnswer(View view) {
        if (ed.getText().toString().length() > 0) {
            YoYo.with(Techniques.FadeOut).duration(500).playOn(view);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button.setEnabled(false);
                        }
                    }, 500);

                }
            });
            Call<User> call = apiBase.submitAnswer(userPreferences.getUsername(), String.valueOf(pos), String.valueOf(ed.getText()));
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    if (response.isSuccessful()) {
                        if (String.valueOf(response.body().message).equals("Congratulations your answer is correct")) {
                            userPreferences.setLevel(String.valueOf(pos));
                            Toasty.success(QuestionActivity.this, "Congratulations your answer is correct!!", Toast.LENGTH_SHORT, true).show();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            finish();
                                        }
                                    }, 1000);
                                }
                            });
                        } else {
                            button.setEnabled(true);
                            Toasty.error(QuestionActivity.this, "Sorry Wrong Answer.Please Try Again!!", Toast.LENGTH_SHORT, true).show();
                            YoYo.with(Techniques.FadeIn).duration(500).playOn(view);
                            button.setEnabled(true);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    button.setEnabled(true);
                    button.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.FadeIn).duration(500).playOn(view);
                    NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(QuestionActivity.this).build();
                    button.setEnabled(true);
                }
            });
        } else {
            button.setEnabled(true);
            ViewTooltip.on(view)
                    .align(ViewTooltip.ALIGN.CENTER)
                    .position(ViewTooltip.Position.TOP)
                    .text("This Field Can't be Empty.")
                    .textColor(Color.WHITE)
                    .color(Color.parseColor("#00BB84"))
                    .padding(2, 2, 2, 2)
                    .corner(15)
                    .arrowWidth(15)
                    .arrowHeight(15)
                    .distanceWithView(5)
                    .show();
            return;
        }
        button.setEnabled(true);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return imageZoomHelper.onDispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    public void playVideo(final String videoId, YouTubePlayerView youTubePlayerView) {
        youTubePlayerView.initialize("AIzaSyCU1NnGxmtqRvM7qJu8XM4d3JxarXbDfzY",
        new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}
