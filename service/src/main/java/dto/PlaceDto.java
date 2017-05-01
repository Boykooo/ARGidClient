package dto;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class PlaceDto {

    @SerializedName("name")
    private String name;

    @SerializedName("address")
    private String address;

    @SerializedName("type")
    private String type;

    @SerializedName("lat")
    private Double lat;

    @SerializedName("lng")
    private Double lng;

    public PlaceDto() {

    }
    public PlaceDto(String name, String address, String type, Double lat, Double lng) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.lat = lat;
        this.lng = lng;
    }

    //region GetSet

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    //endregion
}
