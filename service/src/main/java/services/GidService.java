package services;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import dto.PlaceDto;


/**
 * Created by Andrey on 09.04.2017.
 */


public class GidService {

    private ServerApi api;

    public GidService() {
        api = new ServerApi();
    }

    public List<PlaceDto> getPlaces(String lat, String lng) {
        try {
            JSONArray main = api.getDataByCrd(lat, lng);

            if (main.length() != 0) {

                List<PlaceDto> places = new ArrayList<>();
                for (int i = 0; i < main.length(); i++) {
                    PlaceDto dto = new PlaceDto();
                    dto.setName(main.getJSONObject(i).getString("name"));
                    dto.setAddress(main.getJSONObject(i).getString("address"));
                    dto.setType(main.getJSONObject(i).getString("type"));
                    dto.setLat(main.getJSONObject(i).getDouble("lat"));
                    dto.setLng(main.getJSONObject(i).getDouble("lng"));
                    places.add(dto);
                }
                return places;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
