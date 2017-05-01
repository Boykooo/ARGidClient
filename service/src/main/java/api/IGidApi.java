package api;

import java.util.List;

import dto.PlaceDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IGidApi {
    @GET("/places")
    Call<List<PlaceDto>> getPlaces(@Query("lat") String latitude, @Query("lng") String longitude);
}
