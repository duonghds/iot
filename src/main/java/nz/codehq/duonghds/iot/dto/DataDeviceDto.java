package nz.codehq.duonghds.iot.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class DataDeviceDto {
    private String deviceId;
    private float latitude;
    private float longitude;
    private JsonNode data;

    public DataDeviceDto(String deviceId, float latitude, float longitude, JsonNode data) {
        this.deviceId = deviceId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.data = data;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }
}
