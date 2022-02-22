package ru.gb.gbthymeleafwinter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gb.gbthymeleafwinter.entity.Product;
import ru.gb.gbthymeleafwinter.entity.enums.Status;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto extends AbstractDto<ProductDto> {
    private Long id;
    @NotBlank(message = "title is required")
    private String title;
    @PositiveOrZero
    @Digits(integer = 5, fraction = 2)
    private BigDecimal cost;
    @PastOrPresent
    private LocalDate date;
    //    private Long manufacturerId;
    private Status status;

    public ProductDto(Product product) {
        id = product.getId();
        title = product.getTitle();
        cost = product.getCost();
        date = product.getDate();
        status = product.getStatus();
    }

    public Product convertToProduct() {
        Product product = new Product();

        product.setId(id);
        product.setTitle(title);
        product.setCost(cost);
        product.setDate(date);
        product.setStatus(status);
        return product;
    }
}
