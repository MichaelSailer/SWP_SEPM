package Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class helper {

    static String stream = null;

    public helper() {
    }
    public  static String GetHTTPData(String  urlString) throws IOException {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try {
            if(httpURLConnection.getResponseCode() == 200){
                BufferedReader r = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = r.readLine())!= null)
                    sb.append(line);
                stream = sb.toString();
                httpURLConnection.disconnect();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  stream;
    }

}
