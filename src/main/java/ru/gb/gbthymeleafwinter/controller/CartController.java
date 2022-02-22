package ru.gb.gbthymeleafwinter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbthymeleafwinter.dto.CartDto;
import ru.gb.gbthymeleafwinter.dto.ProductDto;
import ru.gb.gbthymeleafwinter.entity.Cart;
import ru.gb.gbthymeleafwinter.entity.Product;
import ru.gb.gbthymeleafwinter.service.CartService;
import ru.gb.gbthymeleafwinter.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
//Предполагается что у пользавателя будет только одна активная козина
//На текущий момент в системе существует лишь 1 пользователь
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping()
    public Iterable<CartDto> getCarts() {
        Iterable<Cart> carts = cartService.findAll();
        ArrayList<CartDto> cartDtoList = new ArrayList<>();
        carts.forEach(cart -> cartDtoList.add(cart.getDto()));
        return cartDtoList;
    }

    @GetMapping("/products")
    public Iterable<ProductDto> getProducts(){
        Cart cart = cartService.findActive();
        return cart.getProducts().stream().map(Product::getDto).collect(Collectors.toSet());
    }

    @GetMapping("add/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void addProduct(@PathVariable("id") Long productId) {
        List<Cart> allActive = (List<Cart>) cartService.findAllActive();
        Cart cart;
        if (allActive.size() == 0) {
            cart = cartService.save(new Cart());
        } else {
            cart = allActive.get(0);
        }
        cart.getProducts().add(productService.findById(productId));
        cartService.save(cart);
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable("id") Long productId) {
        Cart cart = cartService.findActive();
        List<Product> products = cart.getProducts().stream()
                .filter(product -> product.getId().equals(productId))
                .collect(Collectors.toList());

        if (products.size() != 0) {
            cart.getProducts().remove(products.get(0));
            cartService.save(cart);
        }
    }

    @GetMapping("/buy")
    @ResponseStatus(HttpStatus.OK)
    public void buy() {
        Cart cart = cartService.findActive();
        cartService.disableById(cart.getId());
    }
}
