package nz.codehq.duonghds.iot.service;


import com.fasterxml.jackson.databind.JsonNode;

public interface DataDeviceService {
    void save();
    JsonNode[] getData();
}
