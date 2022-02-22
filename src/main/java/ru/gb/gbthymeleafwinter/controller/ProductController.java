package ru.gb.gbthymeleafwinter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbthymeleafwinter.dto.ProductDto;
import ru.gb.gbthymeleafwinter.entity.Product;
import ru.gb.gbthymeleafwinter.service.ProductService;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Iterable<ProductDto> getProductList() {
        Iterable<Product> products = productService.findAll();
        ArrayList<ProductDto> productsDto = new ArrayList<>();
        products.forEach(product -> productsDto.add(product.getDto()));
        return productsDto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends ProductDto> getProduct(@PathVariable("id") Long id) {
        if (id != null) {
            ProductDto productDto = productService.findById(id).getDto();
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<? extends ProductDto> saveProduct(@Validated @RequestBody ProductDto productDto) {
        ProductDto productDto1 = productService.save(productDto).getDto();
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @Validated @RequestBody ProductDto productDto) {
        productDto.setId(id);
        productService.save(productDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }

}
