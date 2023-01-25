package com.example.exercisenine.controller;

import com.example.exercisenine.ExerciseNineApplication;
import com.example.exercisenine.dto.MeterDto;
import com.example.exercisenine.service.MeterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = ExerciseNineApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MeterRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MeterRestController meterRestController;
    @MockBean
    private MeterService meterService;

    @Test
    void testGetReading() throws Exception {
        MeterDto meterDto = new MeterDto();
        meterDto.setCurrentReading(10.0d);
        meterDto.setDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        meterDto.setMeterGroup("Name");
        meterDto.setMeterId(1L);
        meterDto.setType("Type");
        this.mockMvc.perform(
                        get("/api/v1.0/example")
                                .content(String.valueOf(meterDto))
                                .contentType("application/json"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testExcell() throws Exception {
        this.mockMvc.perform(
                        get("/api/v1.0/excell"))
                .andExpect(status().isOk());
    }

    @Test
    void testGroup() throws Exception {
        this.mockMvc.perform(
                        get("/api/v1.0/group"))
                .andExpect(status().isOk());
    }
}

