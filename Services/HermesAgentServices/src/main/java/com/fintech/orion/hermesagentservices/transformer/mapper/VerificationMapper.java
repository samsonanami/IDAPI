package com.fintech.orion.hermesagentservices.transformer.mapper;

import com.fintech.orion.dto.response.api.VerificationProcessDetail;
import com.fintech.orion.dto.response.external.Verification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {ImageMapper.class})
public interface VerificationMapper {
    @Mappings({
            @Mapping(source = "verificationProcessName", target = "verificationProcessName")
    })
    Verification verificationDetailsToVerification(VerificationProcessDetail verificationProcessDetail);
}
