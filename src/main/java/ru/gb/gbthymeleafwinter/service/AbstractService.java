package ru.gb.gbthymeleafwinter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbthymeleafwinter.dao.AbstractDao;
import ru.gb.gbthymeleafwinter.entity.AbstractEntity;
import ru.gb.gbthymeleafwinter.entity.enums.Status;
import ru.gb.gbthymeleafwinter.exception.NotFoundException;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractService<T extends AbstractEntity<T>> {
    private final AbstractDao<T, Long> repository;

    public Iterable<T> findAllActive(int page, int size) {
        return repository.findByStatus(Status.ACTIVE, PageRequest.of(page, size));
    }

    public Iterable<T> findAllActive() {
        return repository.findByStatus(Status.ACTIVE);
    }

    public Iterable<T> findAllActiveSortedBy(Sort.Direction direction, String column) {
        return repository.findByStatus(Status.ACTIVE, Sort.by(direction, column));
    }

    public Iterable<T> findAllActiveSortedBy(Sort.Direction direction, String column, int page, int size) {
        return repository.findByStatus(Status.ACTIVE, PageRequest.of(page, size, Sort.by(direction, column)));
    }


    public void disableById(Long id) {
        T entity = findById(id);
        entity.setStatus(Status.DISABLE);
        save(entity);
    }

    public void activeById(Long id) {
        T entity = findById(id);
        entity.setStatus(Status.ACTIVE);
        save(entity);
    }

    public T save(T entity) {
        if (entity.getId() != null) {
            T entityFromDb = findById(entity.getId());
            entity = update(entity, entityFromDb);
        }
        return repository.save(entity);
    }

    /**
     * Executed in the method "save", if id != null
     *
     * @param entity
     * @param entityFromDb
     * @return
     */
    protected abstract T update(T entity, T entityFromDb);

    public Iterable<T> saveAll(Iterable<T> entities) {
        return repository.saveAll(entities);
    }

    @Transactional(readOnly = true)
    public T findById(long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Iterable<T> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Iterable<T> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Transactional(propagation = Propagation.NEVER)
    public long count() {
        return repository.count();
    }

    @Transactional(propagation = Propagation.NEVER)
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("There isn't element with id {}", id);
        }
    }

    @Transactional(propagation = Propagation.NEVER)
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void deleteAllById(Iterable<Long> ids) {
        repository.findAllById(ids);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void deleteAll(Iterable<T> entities) {
        repository.deleteAll(entities);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void deleteAll() {
        repository.deleteAll();
    }
}
