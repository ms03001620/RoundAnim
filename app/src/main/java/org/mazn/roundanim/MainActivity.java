package org.mazn.roundanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SampleView mOrange;
    SampleView mGreen;
    SampleView mBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOrange = (SampleView)findViewById(R.id.orange);
        mGreen = (SampleView)findViewById(R.id.green);
        mBlue = (SampleView)findViewById(R.id.blue);

        mOrange.setTotal(10);
        mOrange.setCurrent(9);

        mGreen.setTotal(100);
        mGreen.setCurrent(100);

        mBlue.setTotal(20);
        mBlue.setCurrent(0);
    }



}
