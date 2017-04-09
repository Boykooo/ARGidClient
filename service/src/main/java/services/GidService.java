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
                PlaceDto dto = new PlaceDto();
                String name = main.getJSONObject(0).getString("name");
                dto.setName(name);
                places.add(dto);

                return places;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
