package com.fintech.orion.common;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.io.Serializable;
import java.util.List;

public interface ServiceInterface <E, I extends Serializable>  {

    List<E> getAll();

    E findById(I id) throws ItemNotFoundException;

    void saveOrUpdate(E e);

    void delete(E e);

    boolean deleteById(I id) throws ItemNotFoundException;

}
