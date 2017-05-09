package services;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import api.IGidApi;
import dto.PlaceDto;
import retrofit2.Retrofit;


public class GidService {

    //region Singleton implementation

    private static GidService instance;

    public static GidService getInstance(){
        if (instance != null)
        {
            return instance;
        }
        else {
            instance = new GidService();
            return instance;
        }
    }

    //endregion

    private Retrofit retrofit;
    private IGidApi gidApi;

    private ServerApi serverApi;

    private GidService() {

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(logging)
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(30,TimeUnit.SECONDS).build();
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://91.202.27.32:8080/web/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();

//        gidApi = retrofit.create(IGidApi.class);

        serverApi = new ServerApi();
    }

    public List<PlaceDto> getPlaces(String lat, String lng) {

//        try {
//            List<PlaceDto> places = gidApi.getPlaces(lat, lng).execute().body();
//            return places;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;

        try {
            JSONArray main = serverApi.getDataAboutPlace(lat, lng);
            return parseJson(main);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<PlaceDto> getPlaces(String lat, String lng, String azimuth){
        try {
            JSONArray main = serverApi.getDataAboutPlace(lat, lng, azimuth);
            return parseJson(main);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<PlaceDto> parseJson(JSONArray main){
        try {
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
