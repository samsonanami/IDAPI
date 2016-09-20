package com.fintech.orion.dataabstraction.entities.common;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by ChathurangaRW on 9/13/2016.
 */
public class AbstractConfigDAO<E, I extends Serializable> extends AbstractDAO<E, I> {

    private Class<E> entityClass;

    protected AbstractConfigDAO(Class<E> entityClass) {
        super(entityClass);
        this.entityClass = entityClass;
    }


    @Resource(name = "sessionFactoryForConfig")
    private SessionFactory sessionFactory;


    /**
     * @return
     */
    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

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
}
