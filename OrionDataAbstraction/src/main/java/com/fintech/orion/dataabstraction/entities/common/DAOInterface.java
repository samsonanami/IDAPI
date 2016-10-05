package com.fintech.orion.dataabstraction.entities.common;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

public interface DAOInterface<E, I extends Serializable> {

    Session getCurrentSession();

    E findById(I id) throws ItemNotFoundException;

    void saveOrUpdate(E e);

    void delete(E e);

    List<E> findByCriteria(Criterion criterion);

    List<E> getAll();

    List<E> findByCriteria(List<Criterion> criterionList);

    Object getAllBySort(String sortField, int page, int itemsPerPage);

    Object getAllBySort(String sortField, int page, int itemsPerPage, List<Criterion> criterionList);

    Object getAllBySort(String sortField, SortDirection direction, int page, int itemsPerPage);

    Object getAllBySort(String sortField, SortDirection direction, int page, int itemsPerPage, List<Criterion> criterionList);

    Object getAllBySort(int page, int itemsPerPage, Criteria criteria);
}
