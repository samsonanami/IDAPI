package com.fintech.orion.dataabstraction.entities.common;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by sasitha on 9/12/16.
 */
public abstract class AbstractDAO<E,I extends Serializable> implements DAOInterface<E,I>{

    private Class<E> entityClass;

    protected String itemNotFoundMessage="Item not found";

    /**
     * @param entityClass
     */
    protected AbstractDAO(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public abstract Session getCurrentSession();


    /**
     * @param id
     * @return
     */
    @Override
    public E findById(I id) throws ItemNotFoundException {

        Object entity = getCurrentSession().get(entityClass, id);
        if (entity != null) {
            return (E) getCurrentSession().get(entityClass, id);
        } else {
            throw new ItemNotFoundException(itemNotFoundMessage);
        }

    }

    /**
     * @return
     */
    @Override
    public List<E> getAll() {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        return criteria.list();
    }

    /**
     * @param e
     */
    @Override
    public void saveOrUpdate(E e) {
        getCurrentSession().saveOrUpdate(e);
    }

    /**
     * @param e
     */
    @Override
    public void delete(E e) {
        getCurrentSession().delete(e);
    }

    /**
     * @param criterion
     * @return
     */
    @Override
    public List findByCriteria(Criterion criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        return criteria.list();
    }

    /**
     * @param criterion - a list of criteria
     * @return
     */
    public List findByCriteria(List<Criterion> criterion, String alias, String aliasValue) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.createAlias(aliasValue, alias);
        for (Criterion c : criterion) {
            criteria.add(c);
        }
        return criteria.list();
    }

    /**
     * @param criterion - a list of criteria
     * @return
     */
    public List findByCriteria(List<Criterion> criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        for (Criterion c : criterion) {
            criteria.add(c);
        }
        return criteria.list();
    }


    /**
     * @param criterion - a list of criteria
     * @param sortOrder - sort column
     * @return
     */
    public List findByCriteria(List<Criterion> criterion, String sortOrder) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.addOrder(Order.asc(sortOrder));
        for (Criterion c : criterion) {
            criteria.add(c);
        }
        return criteria.list();
    }

    @Override
    public Object getAllBySort(String sortField, int page, int itemsPerPage) {
        return getAllByPageAndSort(sortField, page, itemsPerPage, null);
    }

    @Override
    public Object getAllBySort(String sortField, int page, int itemsPerPage, List<Criterion> dataCriterionList) {
        return getAllByPageAndSort(sortField, page, itemsPerPage, dataCriterionList);
    }

    @Override
    public Object getAllBySort(String sortField, SortDirection direction, int page, int itemsPerPage) {
        return getAllByPageAndSort(sortField, direction, page, itemsPerPage, null);
    }

    @Override
    public Object getAllBySort(String sortField, SortDirection direction, int page, int itemsPerPage, List<Criterion> dataCriterionList) {
        return getAllByPageAndSort(sortField, direction, page, itemsPerPage, dataCriterionList);
    }

    @Override
    public Object getAllBySort(int page, int itemsPerPage, Criteria dataCriteria) {
        return getAllByPageAndSort(page, itemsPerPage, dataCriteria);
    }

    private PagedDataResult getAllByPageAndSort(String sortField, int page, int itemsPerPage, List<Criterion> dataCriterionList) {
        int pageOffset = (page < 1) ? 1 : page;
        int startIndex = (pageOffset - 1) * itemsPerPage;

        //get the total rows per table
        Criteria countCriteria = getCurrentSession().createCriteria(entityClass);
        countCriteria.setProjection(Projections.rowCount());
        //if there are filters for the data, apply them before getting the total row count
        if (dataCriterionList != null) {
            for (Criterion c : dataCriterionList) {
                countCriteria.add(c);
            }
        }
        Long count = (Long) countCriteria.uniqueResult();

        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.addOrder(Order.asc(sortField));
        criteria.setFirstResult(startIndex);
        criteria.setMaxResults(itemsPerPage);
        //if there are filters for the data, apply them
        if (dataCriterionList != null) {
            for (Criterion c : dataCriterionList) {
                criteria.add(c);
            }
        }

        List<Object> allRows = (ArrayList<Object>) criteria.list();
        PagedDataResult pagedDataResult = new PagedDataResult();
        pagedDataResult.setData(allRows);
        pagedDataResult.setCurrentPage(pageOffset);
        pagedDataResult.setTotalRows(count.intValue());

        return pagedDataResult;
    }

    private PagedDataResult getAllByPageAndSort(String sortField, SortDirection direction, int page, int itemsPerPage, List<Criterion> dataCriterionList) {
        int pageOffset = (page < 1) ? 1 : page;
        int startIndex = (pageOffset - 1) * itemsPerPage;

        //get the total rows per table
        Criteria countCriteria = getCurrentSession().createCriteria(entityClass);
        countCriteria.setProjection(Projections.rowCount());
        //if there are filters for the data, apply them before getting the total row count
        if (dataCriterionList != null) {
            for (Criterion c : dataCriterionList) {
                countCriteria.add(c);
            }
        }
        Long count = (Long) countCriteria.uniqueResult();

        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        if(SortDirection.ASC.equals(direction)){
            criteria.addOrder(Order.asc(sortField));
        } else {
            criteria.addOrder(Order.desc(sortField));
        }
        criteria.setFirstResult(startIndex);
        criteria.setMaxResults(itemsPerPage);
        //if there are filters for the data, apply them
        if (dataCriterionList != null) {
            for (Criterion c : dataCriterionList) {
                criteria.add(c);
            }
        }

        return getDataPage(criteria, count.intValue(), pageOffset);
    }

    private PagedDataResult getAllByPageAndSort(int page, int itemsPerPage, Criteria dataCriteria) {
        int pageOffset = (page < 1) ? 1 : page;
        int startIndex = (pageOffset - 1) * itemsPerPage;

        //get the total rows per table
        dataCriteria.setProjection(Projections.rowCount());
        Long count = (Long) dataCriteria.uniqueResult();

        dataCriteria.setProjection(null);

        dataCriteria.setFirstResult(startIndex);
        dataCriteria.setMaxResults(itemsPerPage);

        return getDataPage(dataCriteria, count.intValue(), pageOffset);
    }

    private PagedDataResult getDataPage(Criteria dataCriteria, int count, int pageOffset){
        List<Object> allRows = (ArrayList<Object>) dataCriteria.list();
        PagedDataResult pagedDataResult = new PagedDataResult();
        pagedDataResult.setData(allRows);
        pagedDataResult.setCurrentPage(pageOffset);
        pagedDataResult.setTotalRows(count);

        return pagedDataResult;
    }
}
