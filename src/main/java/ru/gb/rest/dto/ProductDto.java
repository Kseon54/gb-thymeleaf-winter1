package ru.gb.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.gb.rest.entity.enums.Status;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    @NotBlank(message = "title is required")
    private String title;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 5, fraction = 2)
    private BigDecimal cost;
    @PastOrPresent
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;
    @NotNull
    private Status status;
    private String manufacturer;
}
