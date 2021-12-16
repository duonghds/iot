package nz.codehq.duonghds.iot.mapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nz.codehq.duonghds.iot.dao.DataDeviceEntity;
import nz.codehq.duonghds.iot.dto.DataDeviceDto;
import org.springframework.stereotype.Component;

@Component
public class DataDeviceMapping {

    private ObjectMapper objectMapper = new ObjectMapper();

    public DataDeviceDto entityToDto(DataDeviceEntity entity) {
        JsonNode dataJson = null;
        try{
            dataJson = objectMapper.readTree(entity.getData());
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new DataDeviceDto(
                entity.getDeviceId(),
                entity.getLatitude(),
                entity.getLongitude(),
                dataJson
        );
    }

    public DataDeviceEntity dtoToEntity(DataDeviceDto dto) {
        return new DataDeviceEntity(
                dto.getDeviceId(),
                dto.getLatitude(),
                dto.getLongitude(),
                dto.getData().toString()
        );
    }
}
