package com.fintech.orion.dataabstraction.entities.common;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Created by sasitha on 9/12/16.
 */
public interface DAOInterface<E, I extends Serializable> {

    /**
     * @return
     */
    public Session getCurrentSession();

    /**
     * @param id
     * @return
     */
    public E findById(I id) throws ItemNotFoundException;

    /**
     * @param e
     */
    public void saveOrUpdate(E e);

    /**
     * @param e
     */
    public void delete(E e);

    /**
     * @param criterion
     * @return
     */
    public List findByCriteria(Criterion criterion);

    /**
     * @return
     */
    public List<E> getAll();

    public List findByCriteria(List<Criterion> criterion);

    /**
     * Returns the sorted resultset for the page
     *
     * @param sortField    sortfield to sort by
     * @param page         current page
     * @param itemsPerPage items per page
     */
    public Object getAllBySort(String sortField, int page, int itemsPerPage);

    public Object getAllBySort(String sortField, int page, int itemsPerPage, List<Criterion> dataCriterionList);

    public Object getAllBySort(String sortField, SortDirection direction, int page, int itemsPerPage);

    public Object getAllBySort(String sortField, SortDirection direction, int page, int itemsPerPage, List<Criterion> dataCriterionList);

    public Object getAllBySort(int page, int itemsPerPage, Criteria dataCriteria);
}
