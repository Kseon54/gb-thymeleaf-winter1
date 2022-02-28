package ru.gb.rest.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class ManufacturerDtoTest {

    @Autowired
    private ObjectMapper objectMapper;

    private ManufacturerDto manufacturerDto;

    @BeforeEach
    void setUp() {
        manufacturerDto = ManufacturerDto.builder()
                .manufacturerId(1L)
                .name("Test Company")
                .build();
    }


    @Test
    void testSerializeDto() throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(manufacturerDto);

        System.out.println(jsonString);
    }

    @Test

    void testDeserialize() throws JsonProcessingException {
        String json = "{\"name\":\"Test Company\",\"id\":1}";

        ManufacturerDto testManufacturerDto = objectMapper.readValue(json, ManufacturerDto.class);
        System.out.println(testManufacturerDto);
    }

}