package com.fintech.orion.common;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ChathurangaRW on 10/10/2016.
 */
public interface ServiceInterface <E, I extends Serializable>  {

    E findById(I id) throws ItemNotFoundException;

    void saveOrUpdate(E e);

    void delete(E e);

    List<E> getAll();

    boolean deleteById(I id) throws ItemNotFoundException;

}
