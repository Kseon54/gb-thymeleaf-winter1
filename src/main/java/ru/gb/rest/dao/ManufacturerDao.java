package ru.gb.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.rest.entity.Manufacturer;

import java.util.Optional;

public interface ManufacturerDao extends JpaRepository<Manufacturer, Long> {
    Optional<Manufacturer> findByName(String name);
}
