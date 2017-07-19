package com.fintech.orion.resource;

import com.fintech.orion.dto.validation.file.ValidatorStatus;
import com.fintech.orion.resource.persistence.exception.PersistenceException;

/**
 * Created by TharinduMP on 3/29/2017.
 */
public interface Resource {

    ValidatorStatus validate();

    String persist() throws PersistenceException;

}
