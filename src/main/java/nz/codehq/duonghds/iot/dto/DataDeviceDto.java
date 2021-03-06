package nz.codehq.duonghds.iot.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataDeviceDto {
    private String deviceId;
    private float latitude;
    private float longitude;
    private JsonNode data;
    private Date timestamp;
}
