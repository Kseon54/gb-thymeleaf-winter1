package ru.gb.gbthymeleafwinter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.gb.gbthymeleafwinter.entity.Cart;
import ru.gb.gbthymeleafwinter.entity.enums.Status;

@Setter
@Getter
@AllArgsConstructor
public class CartDto extends AbstractDto<CartDto> {
    private Long id;
    private Status status;

    public CartDto(Cart cart) {
        id = cart.getId();
        status = cart.getStatus();
    }

    public Cart convertToCart() {
        Cart cart = new Cart();

        cart.setId(id);
        cart.setStatus(status);
        return cart;
    }
}
