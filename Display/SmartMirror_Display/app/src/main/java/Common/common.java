package Common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class common {
    public static String API_Key="5bcb287b21a7e0b9d0d82ec6a56b5aad";
    public static String API_Link="https://api.openweathermap.org/data/2.5/weather";

    public static String api_Request(String lat, String log){
        StringBuilder sb = new StringBuilder(API_Link);
        sb.append(String.format("?lat-%s&lon-%s&APPID-%s&untis-metric",lat,log,API_Key));
        return  sb.toString();
    }

    public static String unixTimeStapToDateTime(double unixTimeStap){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime((long)unixTimeStap*1000);
        return  dateFormat.format(date);


    }

    public static String getImage(String icon){
        return  String.format("http://openweather.org/img/w/%s.png",icon);
    }

    public static String getDateNow(){
        DateFormat dateFormat=new SimpleDateFormat("dd MM yyyy HH:mm");
        Date date = new Date();
        return  dateFormat.format(date);

    }


}
