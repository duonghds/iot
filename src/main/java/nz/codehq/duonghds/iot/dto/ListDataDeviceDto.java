package nz.codehq.duonghds.iot.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ListDataDeviceDto {
    private String deviceId;
    private float latitude;
    private float longitude;
    private List<JsonNode> data;
}
