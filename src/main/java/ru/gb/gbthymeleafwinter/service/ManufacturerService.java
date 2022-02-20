package ru.gb.gbthymeleafwinter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.gbthymeleafwinter.dao.ManufacturerDao;
import ru.gb.gbthymeleafwinter.entity.Manufacturer;

@Slf4j
@Service
public class ManufacturerService extends AbstractService<Manufacturer> {

    private final ManufacturerDao manufacturerDao;

    @Autowired
    public ManufacturerService(ManufacturerDao manufacturerDao) {
        super(manufacturerDao);
        this.manufacturerDao = manufacturerDao;
    }

    @Override
    protected Manufacturer update(Manufacturer entity, Manufacturer entityFromDb) {
        entityFromDb.setName(entity.getName());
        entityFromDb.setStatus(entity.getStatus());
//        entityFromDb.setProducts(entity.getProducts());
        return entityFromDb;
    }
}
