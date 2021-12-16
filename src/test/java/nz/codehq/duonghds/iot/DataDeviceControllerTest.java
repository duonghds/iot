package nz.codehq.duonghds.iot;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import nz.codehq.duonghds.iot.controller.DataDeviceController;
import nz.codehq.duonghds.iot.dao.DataDeviceEntity;
import nz.codehq.duonghds.iot.database.DataDeviceRepository;
import nz.codehq.duonghds.iot.service.DataDeviceServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(DataDeviceController.class)
public class DataDeviceControllerTest {
    @MockBean
    DataDeviceRepository dataDeviceRepository;
    @MockBean
    DataDeviceServiceImpl dataDeviceServiceImpl;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void save_success() throws Exception {
        DataDeviceEntity dataDeviceEntity = DataDeviceEntity.builder()
                .deviceId("test_device")
                .latitude(70.0f)
                .longitude(120.0f)
                .data("{\"test_key\":\"test_value\"}")
                .build();
        //Mockito.when(dataDeviceRepository.save(dataDeviceEntity)).thenReturn(dataDeviceEntity);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/devices/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(dataDeviceEntity));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.status", is("success")))
                .andExpect(jsonPath("$.message", is("success")));
    }

    @Test
    public void save_failed_latitude_invalid() throws Exception {
        DataDeviceEntity dataDeviceEntity = DataDeviceEntity.builder()
                .deviceId("test_device")
                .latitude(-190.0f)
                .longitude(120.0f)
                .data("{\"test_key\":\"test_value\"}")
                .build();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/devices/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(dataDeviceEntity));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.status", is("failed")))
                .andExpect(jsonPath("$.message", is("latitude is invalid")));
    }

    @Test
    public void save_failed_longitude_invalid() throws Exception {
        DataDeviceEntity dataDeviceEntity = DataDeviceEntity.builder()
                .deviceId("test_device")
                .latitude(-80.f)
                .longitude(190.0f)
                .data("{\"test_key\":\"test_value\"}")
                .build();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/devices/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(dataDeviceEntity));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.status", is("failed")))
                .andExpect(jsonPath("$.message", is("longitude is invalid")));
    }
}
