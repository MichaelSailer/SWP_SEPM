package com.example.michael.mirror;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AnalogClock;

public class Display extends AppCompatActivity {

    AnalogClock analogClock;
    float größe,addY,addX;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        analogClock = findViewById(R.id.uhr);

        editSizeClock(1000,1000);
        editPostionClock(0,0);
    }



    public void editSizeClock(int height, int width){
            analogClock.setMinimumHeight(height);
            analogClock.setMinimumWidth(width);


    }
    public void editPostionClock(int x, int y){
        analogClock.setY(y+addY);
        analogClock.setX(x+addX);
    }
    public void editVisibilityClock(int id){

    }
}
