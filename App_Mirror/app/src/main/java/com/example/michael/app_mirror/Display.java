package com.example.michael.app_mirror;

import android.content.ClipData;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Display extends AppCompatActivity {
    //Variablen deklation
    ImageButton uhrzeit,wetter,todo;
    TextView target;

    float getXPostionWetter;
    float getXPostionUhrzeit;
    float getXPostionToDo;
    float getYPostionWetter;
    float getYPostionUhrzeit;
    float getYPostionToDo;
    int index = 0;
    char wahl;

    Button button;

    //TextView t = new TextView(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        //Die Elemente der XML Datei wird den Variabeln zugewiesen
        uhrzeit = findViewById(R.id.uhrzeit);
        wetter = findViewById(R.id.wetter);
        todo = findViewById(R.id.toDo);
        target = findViewById(R.id.target);
        button = (Button) findViewById(R.id.button);



        //Die Drag-elemente höheren auf ihren Einsatz
        uhrzeit.setOnLongClickListener(onLongClickListener);
        wetter.setOnLongClickListener(onLongClickListener);
        todo.setOnLongClickListener(onLongClickListener);

        //Höhrt ob er geklickt worden ist
        button.setOnClickListener(onClickListener);

        //Schaut ob ein Objekt zu ihn kommt
        target.setOnDragListener(dragListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Reset alle auf die Alte Position
            uhrzeit.setY(getYPostionUhrzeit);
            uhrzeit.setX(getXPostionUhrzeit);
            wetter.setX(getXPostionWetter);
            wetter.setY(getYPostionWetter);
            todo.setY(getYPostionToDo);
            todo.setX(getXPostionToDo);
            target.setText("Spiegel");
        }
    };


    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            //Es wird ein Schattenelement erzeugt das man bewegen kann
            ClipData clipData = ClipData.newPlainText("","");
            View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(clipData,dragShadowBuilder,v,0);
            return true;
        }
    };
    View.OnDragListener dragListener = new View.OnDragListener(){
    //Der Drag Listener schaut in wleche Lage das objekt sich befindet
        @Override
        public boolean onDrag(View v, DragEvent event) {
           int dragEvent = event.getAction();


            final View view = (View) event.getLocalState();
           switch (dragEvent){
               case DragEvent.ACTION_DRAG_ENTERED:
                   //Falls es auf dem Spiegel kommt wird das gemeldet
                   if(view.getId() == R.id.uhrzeit){
                       target.setText("Uhrzeit is drauf");
                   }
                   break;
               case DragEvent.ACTION_DRAG_EXITED:
                   //Falls das Objekt aus dem Spiegle
                   if(view.getId() == R.id.uhrzeit){
                       target.setText("Uhrzeit ist weg");

                   }
                   break;
               case DragEvent.ACTION_DROP:
                   if(view.getId() == R.id.uhrzeit){
                       target.setText("Uhrzeit ist gedroppt");
                       uhrzeit.setX(event.getX()+200);
                       uhrzeit.setY(event.getY());
                       uhrzeit.bringToFront();
                       uhrzeit.setBackgroundColor(0x91c4f2);

                   }else if(view.getId() == R.id.toDo){
                       target.setText("Wetter ist gedroppt");
                       todo.setX(event.getX()+200);
                       todo.setY(event.getY());
                       todo.bringToFront();
                       todo.setBackgroundColor(0x91c4f2);
                   }else if(view.getId() == R.id.wetter){
                       target.setText("ToDo-Liste ist gedroppt");
                       wetter.setX(event.getX()+200);
                       wetter.setY(event.getY());
                       wetter.bringToFront();
                       wetter.setBackgroundColor(0x91c4f2);
                   }

                   break;
               case DragEvent.ACTION_DRAG_STARTED:
                    if(index == 0) {
                        getXPostionUhrzeit = uhrzeit.getX();
                        getYPostionUhrzeit = uhrzeit.getY();
                        getXPostionWetter = wetter.getX();
                        getYPostionWetter = wetter.getY();
                        getXPostionToDo = todo.getX();
                        getYPostionToDo = todo.getY();
                        index++;
                    }


                   break;
           }
            return true;
        }
    };
}
