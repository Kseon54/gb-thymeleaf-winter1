package ru.gb.rest.dto;


import lombok.*;
import ru.gb.rest.entity.enums.Status;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductManufacturerDto {
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 5, fraction = 2)
    private BigDecimal cost;
    @PastOrPresent
    private LocalDate date;
    private ManufacturerDto manufacturerDto;

}
