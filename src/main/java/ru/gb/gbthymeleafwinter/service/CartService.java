package ru.gb.gbthymeleafwinter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.gbthymeleafwinter.dao.CartDao;
import ru.gb.gbthymeleafwinter.dto.CartDto;
import ru.gb.gbthymeleafwinter.entity.Cart;
import ru.gb.gbthymeleafwinter.exception.NotFoundException;

import java.util.List;

@Slf4j
@Service
public class CartService extends AbstractService<Cart> {

    private final CartDao dao;

    @Autowired
    public CartService(CartDao cartDao) {
        super(cartDao);
        this.dao = cartDao;
    }

    @Override
    protected Cart update(Cart entity, Cart entityFromDb) {
        entityFromDb.setStatus(entity.getStatus());
        entityFromDb.setProducts(entity.getProducts());
        return entityFromDb;
    }

    public Cart save(CartDto entity) {
        if (entity.getId() != null) {
            Cart entityFromDb = findById(entity.getId());
            entityFromDb.setStatus(entity.getStatus());
            entityFromDb.setStatus(entity.getStatus());
        }
        return dao.save(entity.convertToCart());
    }

    public Cart findActive() {
        List<Cart> allActive = (List<Cart>) findAllActive();
        if (allActive.size() != 0) {
            return allActive.get(0);
        } else throw new NotFoundException();

    }
}
