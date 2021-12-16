package nz.codehq.duonghds.iot.database;

import nz.codehq.duonghds.iot.dao.DataDeviceDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataDeviceRepository extends CrudRepository<DataDeviceDao, Long> {
}
