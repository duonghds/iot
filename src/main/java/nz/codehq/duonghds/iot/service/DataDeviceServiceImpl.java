package nz.codehq.duonghds.iot.service;

import com.fasterxml.jackson.databind.JsonNode;
import nz.codehq.duonghds.iot.dao.DataDeviceEntity;
import nz.codehq.duonghds.iot.database.DataDeviceRepository;
import nz.codehq.duonghds.iot.dto.DataDeviceDto;
import nz.codehq.duonghds.iot.mapping.DataDeviceMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DataDeviceServiceImpl implements DataDeviceService{

    @Autowired
    private DataDeviceRepository dataDeviceRepository;

    @Autowired
    private DataDeviceMapping dataDeviceMapping;

    @Override
    public void save(DataDeviceDto dataDeviceDto) {
        DataDeviceEntity dataDeviceEntity = dataDeviceMapping.dtoToEntity(dataDeviceDto);
        dataDeviceRepository.save(dataDeviceEntity);
    }
}
