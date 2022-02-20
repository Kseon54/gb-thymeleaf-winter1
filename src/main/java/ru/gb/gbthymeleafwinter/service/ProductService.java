package ru.gb.gbthymeleafwinter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.gbthymeleafwinter.dao.ProductDao;
import ru.gb.gbthymeleafwinter.entity.Product;

@Slf4j
@Service
public class ProductService extends AbstractService<Product> {

    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        super(productDao);
        this.productDao = productDao;
    }

    @Override
    protected Product update(Product entity, Product entityFromDb) {
        entityFromDb.setTitle(entity.getTitle());
//        entityFromDb.setDate(entity.getDate());
        entityFromDb.setCost(entity.getCost());
//        entityFromDb.setManufacturerId(entity.getManufacturerId());
        entityFromDb.setStatus(entity.getStatus());
        return entityFromDb;
    }
}
