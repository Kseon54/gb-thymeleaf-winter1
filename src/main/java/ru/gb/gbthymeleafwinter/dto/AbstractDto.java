package ru.gb.gbthymeleafwinter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractDto<T> {
    Long id;
}
