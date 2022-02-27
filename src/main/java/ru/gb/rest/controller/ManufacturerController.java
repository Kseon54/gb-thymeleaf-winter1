package ru.gb.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.rest.dto.ManufacturerDto;
//import ru.gb.rest.service.ManufacturerGateway;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/manufacturer")
public class ManufacturerController implements ManufacturerApi {

    private final ManufacturerApi manufacturerApi;

    @Override
    @GetMapping
    public ManufacturerDto create() {
        return manufacturerApi.create();
    }
}
