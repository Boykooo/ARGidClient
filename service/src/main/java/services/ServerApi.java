package services;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

public class ServerApi {

    private String baseRequest;

    public ServerApi() {
        this.baseRequest = "http://91.202.27.32:8080/web/api/places?";
    }

    public JSONArray getDataByCrd(String lat, String lng) throws Exception {
        String request = MessageFormat.format(baseRequest + "lat={0}&lng={1}", lat, lng);

        return new JSONArray(getJsonFromUrl(request));
    }

    private String getJsonFromUrl(String request) {
        InputStream inputStream = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(request);
            connection = (HttpURLConnection) url.openConnection();
            inputStream = connection.getInputStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();
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

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

















