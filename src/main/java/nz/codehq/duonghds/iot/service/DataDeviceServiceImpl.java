package nz.codehq.duonghds.iot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import nz.codehq.duonghds.iot.dao.DataDeviceEntity;
import nz.codehq.duonghds.iot.database.DataDeviceRepository;
import nz.codehq.duonghds.iot.dto.DataDeviceDto;
import nz.codehq.duonghds.iot.dto.ListDataDeviceDto;
import nz.codehq.duonghds.iot.mapping.DataDeviceMapping;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class DataDeviceServiceImpl implements DataDeviceService {

    private final DataDeviceRepository dataDeviceRepository;

    private final DataDeviceMapping dataDeviceMapping;

    public DataDeviceServiceImpl(DataDeviceRepository dataDeviceRepository, DataDeviceMapping dataDeviceMapping) {
        this.dataDeviceRepository = dataDeviceRepository;
        this.dataDeviceMapping = dataDeviceMapping;
    }

    @Override
    public void save(DataDeviceDto dataDeviceDto) {
        DataDeviceEntity dataDeviceEntity = dataDeviceMapping.dtoToEntity(dataDeviceDto);
        dataDeviceRepository.save(dataDeviceEntity);
    }

    @Override
    public ListDataDeviceDto findByDeviceIdAndTime(String deviceId, Date start, Date end) {
        List<DataDeviceEntity> listDataDevices = new ArrayList<>();
        //request without start and end
        if (start == null && end == null) {
            listDataDevices = dataDeviceRepository.findAllByDeviceId(deviceId);
        } else if (start == null) {//request with end
            listDataDevices = dataDeviceRepository.findAllByDeviceIdAndCreatedAtBefore(deviceId, end);
        } else {//request with start or both start and end
            if(end == null) end = new Date();
            listDataDevices = dataDeviceRepository.findAllByDeviceIdAndCreatedAtBetween(deviceId, start, end);
        }

        List<DataDeviceDto> dataDeviceDtos = dataDeviceMapping.entitiesToDtos(listDataDevices);
        if(dataDeviceDtos.size() == 0) {
            return null;
        }
        DataDeviceDto record = dataDeviceDtos.get(0);
        ObjectMapper objectMapper = new ObjectMapper();
        List<JsonNode> listData =  dataDeviceDtos.stream().map(dataDevice -> {
            ObjectNode obj = (ObjectNode) dataDevice.getData();
            obj.put("timestamp", dataDevice.getTimestamp().toString());
            try {
                return objectMapper.readTree(obj.toString());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        return ListDataDeviceDto.builder()
                .deviceId(record.getDeviceId())
                .latitude(record.getLatitude())
                .longitude(record.getLongitude())
                .data(listData)
                .build();
    }
}
