## IoT Project
- This project is test project from CodeHQ, support REST API
to store device data and o retrieve time series of stored
device data.

##Technologies
- Framework: Spring boot
- IDE: Intellij
- Build tool: Gradle
- Lib support: 
    + Spring Boot Starter Data JPA
    + Spring Boot Starter Web
    + MySQL Connector
    + Spring Boot Devtools
    + Lombok
    + Spring Boot Starter Test
    + H2 Database

## API/End-Point
- Record data device: `POST /api/device/`
<br />
Post payload example
```json
{
  "deviceId": "device4",
  "latitude": 41.25,
  "longitude": -120.9762,
  "data": {
    "humidity": 123,
    "temperature": {
      "unit": "C",
      "value": "23.3"
    }
  }
}
```
- Get time-series data device: `GET /api/device/${device_id}`
<br />
Response example
```json
{
    "message": "success",
    "status": "success",
    "data": {
        "deviceId": "device1",
        "latitude": 41.25,
        "longitude": -120.976,
        "data": [
            {
                "humidity": 123,
                "temperature": {
                    "unit": "C",
                    "value": "23.3"
                },
                "timestamp": "2021-12-17 00:16:30.0"
            },
            {
                "humidity": 123,
                "temperature": {
                    "unit": "C",
                    "value": "23.3"
                },
                "timestamp": "2021-12-17 00:16:31.0"
            }
        ]
    }
}
```

## Configuration
Set your config in `src/main/resources/application.properties`

```properties
server.port=${port} #8080
spring.datasource.url = ${mysql_datasource} #jdbc:mysql://localhost:3306/iot
spring.datasource.username = ${mysql_username} #root
spring.datasource.password = ${mysql_password} #root
spring.jpa.hibernate.ddl-auto = update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
```

## How to run project
- You need to setup Java first(JDK/JRE, JAVA_HOME...)
- MySQL Server
- Set config like above
- Run test `./gradlew test`
- Run build `./gradlew clean build`. Built file in `build/libs/something.jar`
- Run `./gradlew bootRun` to run and test local project

## Design pattern
- Pattern: Singleton pattern, DI/IoC pattern. 
Why I use it? Singleton prevents other objects from instantiating 
their own copies of the Singleton object, ensuring that all objects 
access the single instance, so it saves memory. With DI/IOC pattern
you do not care about how services are created and how you get references 
to the ones you need, you just work with interface.

## How I apply SOLID principle
- DataDeviceService just work with DataDeviceDto. It Single Responsibility
- DataDeviceController recive an interface of DataDeviceService, not an implement.
  It Dependency inversion.
- This project too small to applies Open/closed, Liskov Substitution, Interface Segregation principle.
- But with SOLID principle, it should be used correctly and become a habit when coding,
don't push it in.

## Summary breakdown of the approximate time
- Setup(java, mysql, ide, etc.): 1 hour
- Design and structure project: 1 hour
- Coding with test: 4 hours
- Build and testing: 1 hour
- DocumentationL 1 hour

