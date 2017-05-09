package services;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

public class ServerApi {

    private String baseRequest;

    public ServerApi() {
        this.baseRequest = "http://91.202.27.32:8080/web/api/places?";
    }

    public JSONArray getDataAboutPlace(String lat, String lng) throws Exception {
        String request = MessageFormat.format(baseRequest + "lat={0}&lng={1}", lat, lng);

        return new JSONArray(getJsonFromUrl(request));
    }

    public JSONArray getDataAboutPlace(String lat, String lng, String azimuth){
        String request = MessageFormat.format(baseRequest + "lat={0}&lng={1}&azimuth={2}", lat, lng, azimuth);

        return new JSONArray(getJsonFromUrl(request));
    }

    private String getJsonFromUrl(String request) {
        InputStream inputStream = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(request);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            inputStream = connection.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            inputStream.close();

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

















