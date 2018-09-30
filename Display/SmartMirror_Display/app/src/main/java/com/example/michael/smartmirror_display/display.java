package com.example.michael.smartmirror_display;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;

import Common.common;
import Model.*;
import  Helper.*;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;
import static java.lang.Enum.valueOf;

public class display extends AppCompatActivity implements LocationListener {
    TextView txtCity, txtDecription, txtHumidity, txtCelsius;
    ImageView imageView;
    String provider;

    LocationManager locationManager;

    static double lat, log;
    openWeatherMap openWeatherMap = new openWeatherMap();
    int My_Permission = 0;

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(display.this,new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION

            },My_Permission);
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        //Controll
        txtCelsius = (TextView) findViewById(R.id.txtCelsius);
        txtCity = (TextView) findViewById(R.id.txtcity);
        txtDecription = (TextView) findViewById(R.id.txtDecription);
        txtHumidity = (TextView) findViewById(R.id.txtHumidity);
        imageView = (ImageView) findViewById(R.id.imageview);





        //Hier bekommt man die Koordinaten
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        provider = locationManager.getBestProvider( new Criteria(), false );



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(display.this,new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION
            },My_Permission);
        }

        Location location = locationManager.getLastKnownLocation(provider);
        if(location == null){
            Log.e("Tag","Location not found");

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        log = location.getLongitude();

        new GetWeather().execute(common.api_Request(String.valueOf(lat),String.valueOf(log)));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {


    }

    private class GetWeather extends AsyncTask<String,Void,String>{

        ProgressDialog pd = new ProgressDialog(display.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Bitte warten...");
            pd.show();

        }

        @Override
        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];

            helper http = new helper();
            try {
                stream=http.GetHTTPData(urlString);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.contains("Error: Not found City")){
                pd.dismiss();
                String x ="Error!!!";
                x.toString();
                return;
            }
            Gson gson = new Gson();
            Type type = new TypeToken<openWeatherMap>(){}.getType();
            openWeatherMap = gson.fromJson(s,type);
            pd.dismiss();

            txtCity.setText(String.format("%s,%s", openWeatherMap.getName(),openWeatherMap.getSys().getCountry()));
            txtDecription.setText(String.format("%s",openWeatherMap.getWeather().get(0).getDescription()));
            txtHumidity.setText(String.format("%d%%",openWeatherMap.getMain().getHumidity()));
            txtCelsius.setText(String.format("%.2f °C",openWeatherMap.getMain().getTemp()));
            Picasso.get()
                    .load(common.getImage(openWeatherMap.getWeather().get(0).getIcon()))
                    .into(imageView);
            
        }
    }
}
