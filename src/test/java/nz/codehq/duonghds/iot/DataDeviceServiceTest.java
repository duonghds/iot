package nz.codehq.duonghds.iot;

import nz.codehq.duonghds.iot.database.DataDeviceRepository;
import nz.codehq.duonghds.iot.dto.ListDataDeviceDto;
import nz.codehq.duonghds.iot.entity.DataDeviceEntity;
import nz.codehq.duonghds.iot.mapper.DataDeviceMapper;
import nz.codehq.duonghds.iot.service.DataDeviceService;
import nz.codehq.duonghds.iot.service.DataDeviceServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class DataDeviceServiceTest {

    private final DataDeviceMapper dataDeviceMapper = new DataDeviceMapper();
    @MockBean
    DataDeviceRepository dataDeviceRepository;
    @Autowired
    DataDeviceService dataDeviceService = new DataDeviceServiceImpl(dataDeviceRepository, dataDeviceMapper);
    @Test
    public void testGetByServiceId_success() {
        Mockito.when(dataDeviceRepository.findAllByDeviceId("test_id"))
                .thenReturn(Arrays.asList(
                        DataDeviceEntity.builder()
                                .deviceId("test_id").data("{\"a\":\"b\"}")
                                .createdAt(new Date()).latitude(20f).build(),
                        DataDeviceEntity.builder()
                                .deviceId("test_id").data("{\"a\":\"b\"}")
                                .createdAt(new Date()).latitude(20f).build(),
                        DataDeviceEntity.builder()
                                .deviceId("test_id").data("{\"a\":\"b\"}")
                                .createdAt(new Date()).latitude(20f).build()
                ));
        ListDataDeviceDto listDataDeviceDto = dataDeviceService.findByDeviceIdAndTime("test_id", null, null);
        Assertions.assertThat(listDataDeviceDto.getData().size()).isGreaterThan(2);
    }

    @Test
    public void testGetByServiceId_not_found() {
        Mockito.when(dataDeviceRepository.findAllByDeviceId("test_id"))
                .thenReturn(Arrays.asList(

                ));
        ListDataDeviceDto listDataDeviceDto = dataDeviceService.findByDeviceIdAndTime("test_id", null, null);
        Assertions.assertThat(listDataDeviceDto).isNull();
    }
}
