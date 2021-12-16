package nz.codehq.duonghds.iot.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nz.codehq.duonghds.iot.entity.DataDeviceEntity;
import nz.codehq.duonghds.iot.dto.DataDeviceDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataDeviceMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public DataDeviceDto entityToDto(DataDeviceEntity entity) {
        JsonNode dataJson = null;
        try {
            dataJson = objectMapper.readTree(entity.getData());
        } catch (JsonProcessingException e) {
            //just pass/ignore data invalid and log it
            System.out.printf("[Mapping data invalid]%s - %s\n", entity.getDeviceId(), entity.getData());
            e.printStackTrace();

        }
        return new DataDeviceDto(
                entity.getDeviceId(),
                entity.getLatitude(),
                entity.getLongitude(),
                dataJson,
                entity.getCreatedAt()

        );
    }

    public List<DataDeviceDto> entitiesToDtos(List<DataDeviceEntity> entities) {
        List<DataDeviceDto> dtos = new ArrayList<>();
        for (DataDeviceEntity entity : entities) {
            DataDeviceDto dto = entityToDto(entity);
            dtos.add(dto);
        }
        return dtos;
    }

        public DataDeviceEntity dtoToEntity (DataDeviceDto dto){
            return DataDeviceEntity.builder()
                    .deviceId(dto.getDeviceId())
                    .latitude(dto.getLatitude())
                    .longitude(dto.getLongitude())
                    .data(dto.getData().toString())
                    .build();
        }
    }
