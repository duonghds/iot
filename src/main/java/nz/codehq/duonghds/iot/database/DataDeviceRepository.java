package nz.codehq.duonghds.iot.database;

import nz.codehq.duonghds.iot.dao.DataDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataDeviceRepository extends JpaRepository<DataDeviceEntity, Long> {

}
