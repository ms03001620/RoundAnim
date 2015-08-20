package org.mazn.roundanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ProgressRingView mOrange;
    ProgressRingView mGreen;
    ProgressRingView mBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOrange = (ProgressRingView)findViewById(R.id.orange);
        mGreen = (ProgressRingView)findViewById(R.id.green);
        mBlue = (ProgressRingView)findViewById(R.id.blue);

        mOrange.setTotal(10);
        mOrange.setCurrent(9);

        mGreen.setTotal(100);
        mGreen.setCurrent(100);

        mBlue.setTotal(20);
        mBlue.setCurrent(0);
    }



}
