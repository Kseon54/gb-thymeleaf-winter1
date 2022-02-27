package ru.gb.rest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.rest.dao.ManufacturerDao;
import ru.gb.rest.dao.ProductDao;
import ru.gb.rest.dto.ProductDto;
import ru.gb.rest.dto.ProductManufacturerDto;
import ru.gb.rest.dto.mapper.ProductMapper;
import ru.gb.rest.entity.Product;
import ru.gb.rest.entity.enums.Status;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductDao productDao;
    private final ProductMapper productMapper;
    private final ManufacturerDao manufacturerDao;

    @Transactional
    public ProductDto save(ProductDto productDto) {
        Product product = productMapper
                .toProduct(productDto, manufacturerDao);
        if (product.getId() != null) {
            productDao.findById(productDto.getId()).ifPresent(
                    p -> product.setVersion(p.getVersion())
            );
        }
        return productMapper.toProductDto(productDao.save(product));
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        return productMapper.toProductDto(productDao.findById(id).orElse(null));
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        return productDao.findAll().stream().map(productMapper::toProductDto).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        try {
            productDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("There isn't product with id {}", id);
        }

    }

    public List<ProductManufacturerDto> findAllInfo() {
        return productDao.findAll().stream().map(productMapper::toProductManufacturerDto).collect(Collectors.toList());
    }


    public void disableById(Long id) {
        productDao.findById(id).ifPresent(
                p -> {
                    p.setStatus(Status.DISABLE);
                    productDao.save(p);
                }
        );
    }

    public List<Product> findAllActive() {
        return productDao.findAllByStatus(Status.ACTIVE);
    }


    public List<Product> findAllActive(int page, int size) {
        return productDao.findAllByStatus(Status.ACTIVE, PageRequest.of(page, size));
    }

    public List<Product> findAllActiveSortedById(Sort.Direction direction) {
        return productDao.findAllByStatus(Status.ACTIVE, Sort.by(direction, "id"));
    }

    public List<Product> findAllActiveSortedById(int page, int size, Sort.Direction direction) {
        return productDao.findAllByStatus(Status.ACTIVE, PageRequest.of(page, size, Sort.by(direction, "id")));
    }

    @Transactional(propagation = Propagation.NEVER)
    public long count() {
        System.out.println(productDao.count());
        // какая-то логика
        return productDao.count();
    }
}
