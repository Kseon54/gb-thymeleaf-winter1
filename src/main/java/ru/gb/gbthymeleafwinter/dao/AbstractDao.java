package ru.gb.gbthymeleafwinter.dao;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.gb.gbthymeleafwinter.entity.AbstractEntity;
import ru.gb.gbthymeleafwinter.entity.enums.Status;

@NoRepositoryBean
public interface AbstractDao<T extends AbstractEntity<T>,ID> extends CrudRepository<T, ID> {

    Iterable<T> findByStatus(Status status, PageRequest pageRequest);

    Iterable<T> findByStatus(Status status);

    Iterable<T> findByStatus(Status active, Sort by);
}
