package com.fintech.orion.api.service.validator;

import com.fintech.orion.api.service.exceptions.ResourceAccessPolicyViolationException;

import java.util.List;

/**
 * Created by sasitha on 2/10/17.
 *
 */
public interface ResourceAccessValidatorInterface {
    boolean validateResourceOwnership(String userName, List<String> resourceList);
}
