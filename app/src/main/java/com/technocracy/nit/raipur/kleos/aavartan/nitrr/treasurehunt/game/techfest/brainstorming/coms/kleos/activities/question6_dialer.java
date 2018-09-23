package com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.R;

import java.util.Random;

public class question6_dialer extends Activity  implements View.OnClickListener{
    public TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10;
    public Button B1,B2,B3,B4,B5,B6,B7,B8,B9,B10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qn6);
        tv1 = (TextView) findViewById(R.id.tv1);B1 = (Button) findViewById(R.id.B1);
        tv2 = (TextView) findViewById(R.id.tv2);B2 = (Button) findViewById(R.id.B2);
        tv3 = (TextView) findViewById(R.id.tv3);
        B3 = (Button) findViewById(R.id.B3); tv4 = (TextView) findViewById(R.id.tv4);
        B4 = (Button) findViewById(R.id.B4); tv5 = (TextView) findViewById(R.id.tv5);
        B5 = (Button) findViewById(R.id.B5); tv6 = (TextView) findViewById(R.id.tv6);
        B6 = (Button) findViewById(R.id.B6); tv7 = (TextView) findViewById(R.id.tv7);
        B7 = (Button) findViewById(R.id.B7); tv8 = (TextView) findViewById(R.id.tv8);
        B8 = (Button) findViewById(R.id.B8); tv9 = (TextView) findViewById(R.id.tv9);
        B9 = (Button) findViewById(R.id.B9); tv10 = (TextView) findViewById(R.id.tv10);
        B10 = (Button) findViewById(R.id.B10);
        B1.setOnClickListener(this);
        B2.setOnClickListener(this);
        B3.setOnClickListener(this);
        B4.setOnClickListener(this);
        B5.setOnClickListener(this);
        B6.setOnClickListener(this);
        B7.setOnClickListener(this);
        B8.setOnClickListener(this);
        B9.setOnClickListener(this);
        B10.setOnClickListener(this);

    }
        public void onClick(View v) {

            char c = random();

            B1.setText(""+c);
            char c2 = (char) ((char) c+8);
            tv1.setText(""+c2);

            c = random();
            B2.setText(""+c);
            c2 = (char) ((char)c+3);
            tv2.setText(""+c2);

            c = random();
            B3.setText(""+c);
            c2 = (char) ((char)c+4);
            tv3.setText(""+c2);

            c = random();
            B4.setText(""+c);
            c2 = (char) ((char)c+9);
            tv4.setText(""+c2);

            c = random();
            B5.setText(""+c);
            tv5.setText(""+c);

            c = random();
            B6.setText(""+c);
            c2 = (char) ((char)c+4);
            tv6.setText(""+c2);

            c = random();
            B7.setText(""+c);
            c2 = (char) ((char)c+3);
            tv7.setText(""+c2);

            c = random();
            B8.setText(""+c);
            tv8.setText(""+c);

            c = random();
            B9.setText(""+c);
            c2 = (char) ((char)c+2);
            tv9.setText(""+c2);

            c = random();
            B10.setText(""+c);
            c2 = (char) ((char)c+7);
            tv10.setText(""+c2);


        }
    public char random(){
        Random r = new Random();
        int Low = 65;
        int High = 81;
        int result = r.nextInt(High-Low) + Low;
        return (char) result;
    }


    public void exit(View view) {
        finish();
    }
}


