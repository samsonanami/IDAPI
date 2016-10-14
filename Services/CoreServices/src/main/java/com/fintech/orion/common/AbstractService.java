package com.fintech.orion.common;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class AbstractService<E,I extends Serializable> implements ServiceInterface<E, I> {

    private AbstractDAO<E, I> abstractDAO;

    @Override
    @Transactional
    public List<E> getAll() {
        return abstractDAO.getAll();
    }

    @Override
    @Transactional
    public E findById(I id) throws ItemNotFoundException {
        return abstractDAO.findById(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(E e) {
        abstractDAO.saveOrUpdate(e);
    }

    @Override
    @Transactional
    public void delete(E e) {
        abstractDAO.delete(e);
    }

    @Override
    @Transactional
    public boolean deleteById(I id) throws ItemNotFoundException {
        E e = abstractDAO.findById(id);
        if(e != null){
            abstractDAO.delete(e);
            return true;
        }
        return false;
    }

}
