package nz.codehq.duonghds.iot.dao;

import javax.persistence.*;

@Entity
@Table(name = "data_device")
public class DataDeviceDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String deviceID;
    private float latitude;
    private float longitude;
    private String data;
}
