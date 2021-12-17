package nz.codehq.duonghds.iot.service;


import nz.codehq.duonghds.iot.dto.DataDeviceDto;
import nz.codehq.duonghds.iot.dto.ListDataDeviceDto;
public interface DataDeviceService {
    void save(DataDeviceDto dataDeviceDto);
    ListDataDeviceDto findByDeviceIdAndTime(String deviceId, Long start, Long end);
}
