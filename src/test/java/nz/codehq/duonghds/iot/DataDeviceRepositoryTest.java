package nz.codehq.duonghds.iot;

import nz.codehq.duonghds.iot.entity.DataDeviceEntity;
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

import java.util.Date;
import java.util.List;

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

    @Test
    @Order(1)
    @Rollback(value = false)
    public void findByDeviceId() {
        DataDeviceEntity dataDeviceEntity = DataDeviceEntity.builder()
                .deviceId("device_test")
                .latitude(70.0f)
                .longitude(120.0f)
                .data("{\"test\":\"test_value\"}")
                .build();

        for(int i=0;i<5;i++) {
            dataDeviceRepository.save(dataDeviceEntity);
        }
        List<DataDeviceEntity> deviceTestList = dataDeviceRepository.findAllByDeviceId("device_test");
        Assertions.assertThat(deviceTestList.size()).isGreaterThan(4);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void findByDeviceIdAndTime() {
        Date addDate = new Date();
        Date start = new Date(addDate.getTime() - 3000);//before 3 second
        Date end = new Date(addDate.getTime() + 3000);//after 3 second
        DataDeviceEntity dataDeviceEntity = DataDeviceEntity.builder()
                .deviceId("device_test")
                .latitude(70.0f)
                .longitude(120.0f)
                .data("{\"test\":\"test_value\"}")
                .createdAt(addDate)
                .build();
        DataDeviceEntity save = dataDeviceRepository.save(dataDeviceEntity);
        System.out.println("save = " + save);
        List<DataDeviceEntity> deviceTestList = dataDeviceRepository.findAllByDeviceIdAndCreatedAtBetween("device_test", start,end);
        Assertions.assertThat(deviceTestList.size()).isGreaterThan(0);
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void findByDeviceIdAndOnlyStart() {
        Date addDate = new Date();
        Date start = new Date(addDate.getTime() - 3000); //before 3 second
        DataDeviceEntity dataDeviceEntity = DataDeviceEntity.builder()
                .deviceId("device_test")
                .latitude(70.0f)
                .longitude(120.0f)
                .data("{\"test\":\"test_value\"}")
                .createdAt(addDate)
                .build();
        DataDeviceEntity save = dataDeviceRepository.save(dataDeviceEntity);
        System.out.println("save = " + save);
        List<DataDeviceEntity> deviceTestList = dataDeviceRepository.findAllByDeviceIdAndCreatedAtBefore("device_test", start);
        Assertions.assertThat(deviceTestList.size()).isGreaterThan(0);
    }
}
