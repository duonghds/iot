package nz.codehq.duonghds.iot.service;

import com.fasterxml.jackson.databind.JsonNode;
import nz.codehq.duonghds.iot.database.DataDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DataDeviceImpl implements DataDeviceService{

    @Autowired
    private DataDeviceRepository dataDeviceRepository;

    @Override
    public void save() {

    }

    @Override
    public JsonNode[] getData() {
        return new JsonNode[0];
    }
}
