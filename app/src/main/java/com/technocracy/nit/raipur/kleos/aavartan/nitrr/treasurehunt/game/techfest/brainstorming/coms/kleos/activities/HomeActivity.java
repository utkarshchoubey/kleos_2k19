package com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.depth.Depth;
import com.github.florent37.depth.DepthListener;
import com.github.florent37.depth.DepthProvider;
import com.github.florent37.depth.animations.DepthAnimation;
import com.github.florent37.depth.animations.EnterConfiguration;
import com.github.florent37.depth.animations.ExitConfiguration;
import com.github.florent37.depth.animations.ReduceConfiguration;
import com.mursaat.extendedtextview.AnimatedGradientTextView;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.R;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.fragments.HintsFragment;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.fragments.LeaderboardFragment;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.fragments.ProfileFragment;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.fragments.QuestionsFragment;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.utils.UserPreferences;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

import static maes.tech.intentanim.CustomIntent.customType;

public class HomeActivity extends AppCompatActivity {
    AnimatedGradientTextView homeTextview;
    RelativeLayout mainRelativeLayout;
    FrameLayout mainView;
    BottomNavigation bottomNavigation;
    int prevFragmentPos = 0;
    int nextFragmentPos = 0;
    ConstraintLayout mainLayout;
    UserPreferences preferences;
    boolean doubleBackToExitPressedOnce = false;
    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        customType(this, "fadein-to-fadeout");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = new UserPreferences(this);

        mainLayout = findViewById(R.id.activity_home);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        FrameLayout fm = findViewById(R.id.mainFrameLayout);
        final Depth depth = DepthProvider.getDepth(fm);
        depth.setFragmentContainer(R.id.mainFrameLayout);
        depth.animate().enter(new QuestionsFragment()).start();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                }, 1200);

            }
        });


        homeTextview = findViewById(R.id.kleos);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {
            @Override
            public void onMenuItemSelect(int itemID, int position, boolean fromUser) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Fragment oldFragment = getSupportFragmentManager().findFragmentById(R.id.mainFrameLayout);
                if (oldFragment==null){
                    switch (prevFragmentPos){
                        case 0: oldFragment = new QuestionsFragment(); break;
                        case 1: oldFragment = new LeaderboardFragment(); break;
                        case 2: oldFragment = new HintsFragment(); break;
                        case 3: oldFragment = new ProfileFragment(); break;
                        default: oldFragment = new QuestionsFragment();
                    }
                }
                Fragment newFragment;
                switch (itemID){
                    case R.id.questionTab:
                        nextFragmentPos =0;
                        homeTextview.setText("Questions");
                        newFragment = new QuestionsFragment();
                        break;
                    case R.id.leaderboardtab:
                        nextFragmentPos = 1;
                        homeTextview.setText("Leaderboard");
                        newFragment = new LeaderboardFragment();
                        break;
                    case R.id.hintTab:
                        nextFragmentPos = 2;
                        homeTextview.setText("Hints");
                        newFragment = new HintsFragment();
                        break;
                    case R.id.profileTab:
                        nextFragmentPos = 3;
                        homeTextview.setText("Profile");
                        newFragment = new ProfileFragment();
                        break;
                    default: nextFragmentPos = 0;
                        homeTextview.setText("Questions");
                        newFragment = new QuestionsFragment();
                }

                depth.animate()
                        .reduce(oldFragment, new ReduceConfiguration().setDuration(300))
                        .exit(oldFragment, new ExitConfiguration().setDuration(300))
                        .enter(newFragment, new EnterConfiguration().setDuration(400))
                        .start();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                        }, 1100);

                    }
                });
                prevFragmentPos = position;
            }
            @Override
            public void onMenuItemReselect(int itemID, int position, boolean fromUser) {
            }
        });
        depth.addListener(new DepthListener() {
            @Override
            public void onAnimationEnd(DepthAnimation depthAnimation, Fragment fragment) {
              if(fragment == null){
                  Fragment oldFragment;
                  Fragment newFragment;
                  switch (prevFragmentPos){
                          case 0: oldFragment = new QuestionsFragment(); break;
                          case 1: oldFragment = new LeaderboardFragment(); break;
                          case 2: oldFragment = new HintsFragment(); break;
                          case 3: oldFragment = new ProfileFragment(); break;
                          default: oldFragment = new QuestionsFragment();
                  }
                  switch (nextFragmentPos){
                      case 0: newFragment = new QuestionsFragment();
                          homeTextview.setText("Questions");
                      break;
                      case 1:
                          homeTextview.setText("Leaderboard");
                          newFragment = new LeaderboardFragment(); break;
                      case 2:
                          homeTextview.setText("Hints");
                          newFragment = new HintsFragment(); break;
                      case 3:
                          homeTextview.setText("Profile");
                          newFragment = new ProfileFragment(); break;
                      default:
                          homeTextview.setText("Questions");
                          newFragment = new QuestionsFragment();
                  }
                  depth.animate()
                          .reduce(oldFragment, new ReduceConfiguration().setDuration(300))
                          .exit(oldFragment, new ExitConfiguration().setDuration(300))
                          .enter(newFragment, new EnterConfiguration().setDuration(400))
                          .start();
              }

            }
        });
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.navigation_drawer)
                .inject();
        View v = slidingRootNav.getLayout().getChildAt(0);
        CircleImageView imageView = v.findViewById(R.id.drawerImg);
        imageView.setImageURI(preferences.getProfileImage());
        TextView nameV = v.findViewById(R.id.drawerName);
        nameV.setText(preferences.getName());
        TextView levelV = v.findViewById(R.id.drawerLevel);
        levelV.setText("");
        Button logout = v.findViewById(R.id.logoutB);
//        Button teamB =(Button) v.findViewById(R.id.teamB);
        Button storylineB = v.findViewById(R.id.storylineB);
        Button sponsorsB = v.findViewById(R.id.sponsorsB);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.clearPrefs();
                Intent i = new Intent(HomeActivity.this, SplashScreen.class);
                startActivity(i);
                finish();
            }
        });
//        teamB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(HomeActivity.this, TeamActivity.class);
//                startActivity(i);
//            }
//        });
        storylineB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, StoryLineActivity.class);
                startActivity(i);
            }
        });
        sponsorsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, SponsorsActivity.class);
                startActivity(i);
            }
        });
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
        Toasty.info(this, "Please click BACK again to exit", Toast.LENGTH_SHORT,true).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}
