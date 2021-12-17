package nz.codehq.duonghds.iot.controller;

import nz.codehq.duonghds.iot.dto.BaseResponseDto;
import nz.codehq.duonghds.iot.dto.DataDeviceDto;
import nz.codehq.duonghds.iot.dto.ListDataDeviceDto;
import nz.codehq.duonghds.iot.service.DataDeviceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/api/devices")
public class DataDeviceController {

    private final DataDeviceService dataDeviceService;

    public DataDeviceController(DataDeviceService dataDeviceService) {
        this.dataDeviceService = dataDeviceService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<BaseResponseDto<?>> saveDeviceData(@Validated @RequestBody DataDeviceDto data) {
        if(data.getDeviceId().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponseDto<>("deviceId is empty", "failed", null));
        }
        if(data.getLatitude() > 90 || data.getLatitude() < -90) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponseDto<>("latitude is invalid", "failed", null));
        }
        if(data.getLongitude() > 180 || data.getLongitude() < -180) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponseDto<>("longitude is invalid", "failed", null));
        }
        dataDeviceService.save(data);

        return ResponseEntity
                .ok()
                .body(new BaseResponseDto<>("success", "success", null));
    }

    @GetMapping(path = "/{deviceId}")
    public ResponseEntity<BaseResponseDto<ListDataDeviceDto>> getDevicesData(
            @PathVariable(value = "deviceId") String deviceId,
            @RequestParam(name = "start", required = false ) Long start,
            @RequestParam(name = "end", required = false ) Long end) {
        System.out.println("start = " + start);
        System.out.println("end = " + end);
        ListDataDeviceDto listDataDevice = dataDeviceService.findByDeviceIdAndTime(deviceId, start, end);
        if(listDataDevice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new BaseResponseDto<>("success", "success", listDataDevice));
    }
}
