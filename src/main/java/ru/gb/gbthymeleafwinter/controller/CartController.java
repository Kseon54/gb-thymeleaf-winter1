package ru.gb.gbthymeleafwinter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.gbthymeleafwinter.entity.Cart;
import ru.gb.gbthymeleafwinter.entity.Product;
import ru.gb.gbthymeleafwinter.service.CartService;
import ru.gb.gbthymeleafwinter.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
//Предполагается что у пользавателя будет только одна активная козина
//На текущий момент в системе существует лишь 1 пользователь
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping()
    public String getProductList(Model model) {
        List<Cart> allActive = cartService.findAllActive();
        if (allActive.size()!=0){
            Cart cart = allActive.get(0);
            model.addAttribute("products",cart.getProducts());
        }
        return "cart-listProduct";
    }

    @GetMapping("add")
    public String addProduct(@RequestParam(name = "id", required = false) Long productId) {
        List<Cart> allActive = cartService.findAllActive();
        Cart cart;
        if (allActive.size()==0){
            cart = cartService.save(new Cart());
        }else {
            cart = allActive.get(0);
        }
        cart.getProducts().add(productService.findById(productId));
        cartService.save(cart);
        return "redirect:/product/all";
    }

    @GetMapping("/deleteProduct")
    public String deleteProductById(@RequestParam(name = "idProduct") Long idProduct) {
        List<Cart> allActive = cartService.findAllActive();
        if (allActive.size()!=0){
            Cart cart = allActive.get(0);
            List<Product> products = cart.getProducts().stream()
                    .filter(product -> product.getId().equals(idProduct))
                    .collect(Collectors.toList());

            if (products.size()!=0){
                cart.getProducts().remove(products.get(0));
                cartService.save(cart);
            }
        }
        return "redirect:/cart";
    }
}
