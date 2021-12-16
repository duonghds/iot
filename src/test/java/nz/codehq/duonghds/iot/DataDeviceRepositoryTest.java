package nz.codehq.duonghds.iot;

import nz.codehq.duonghds.iot.dao.DataDeviceEntity;
import nz.codehq.duonghds.iot.database.DataDeviceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataDeviceRepositoryTest {

    @Autowired
    private DataDeviceRepository dataDeviceRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveDataDeviceTest() {
        DataDeviceEntity dataDeviceEntity = DataDeviceEntity.builder()
                .deviceId("device_test")
                .latitude(70.0f)
                .longitude(120.0f)
                .data("{\"test\":\"test_value\"}")
                .build();
        dataDeviceRepository.save(dataDeviceEntity);
        Assertions.assertThat(dataDeviceEntity.getId()).isGreaterThan(0);
    }
}
