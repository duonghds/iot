package nz.codehq.duonghds.iot.database;

import nz.codehq.duonghds.iot.entity.DataDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DataDeviceRepository extends JpaRepository<DataDeviceEntity, Long> {
    List<DataDeviceEntity> findAllByDeviceId(String deviceId);
    List<DataDeviceEntity> findAllByDeviceIdAndCreatedAtBetween(String deviceId, Date start, Date end);
    List<DataDeviceEntity> findAllByDeviceIdAndCreatedAtBefore(String deviceId, Date start);
}
