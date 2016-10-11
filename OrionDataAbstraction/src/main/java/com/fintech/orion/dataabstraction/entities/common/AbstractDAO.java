package com.fintech.orion.dataabstraction.entities.common;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDAO<E,I extends Serializable> implements DAOInterface<E,I>{

    private static final String UNSUPPORTED_OPERATION_MESSAGE = "Unsupported Operation";
    protected String itemNotFoundMessage = "Item not found";
    private Class<E> entityClass;
    @Autowired
    private SessionFactory sessionFactory;

    protected AbstractDAO(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public E findById(I id) throws ItemNotFoundException {
        Object entity = getCurrentSession().get(entityClass, id);
        if (entity != null) {
            return (E) entity;
        } else {
            throw new ItemNotFoundException(itemNotFoundMessage);
        }
    }

    @Override
    public void saveOrUpdate(E e) {
        getCurrentSession().saveOrUpdate(e);
    }

    @Override
    public void delete(E e) {
        getCurrentSession().delete(e);
    }

    @Override
    public List<E> findByCriteria(Criterion criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        return criteria.list();
    }

    @Override
    public List<E> getAll() {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        return criteria.list();
    }

    @Override
    public List<E> findByCriteria(List<Criterion> criterionList) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        for (Criterion criterion : criterionList) {
            criteria.add(criterion);
        }
        return criteria.list();
    }

    @Override
    public Object getAllBySort(String sortField, int page, int itemsPerPage) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }

    @Override
    public Object getAllBySort(String sortField, int page, int itemsPerPage, List<Criterion> criterionList) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }

    @Override
    public Object getAllBySort(String sortField, SortDirection direction, int page, int itemsPerPage) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }

    @Override
    public Object getAllBySort(String sortField, SortDirection direction, int page, int itemsPerPage, List<Criterion> criterionList) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }

    @Override
    public Object getAllBySort(int page, int itemsPerPage, Criteria criteria) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }
}
