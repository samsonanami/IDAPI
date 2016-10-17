package com.fintech.orion.dto.clientlicense;

import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.dto.license.LicenseDTO;

/**
 * Created by ChathurangaRW on 10/17/2016.
 */
public class ClientLicenseDTO {

    private Integer id;
    private ClientDTO clientDTO;
    private LicenseDTO licenseDTO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public LicenseDTO getLicenseDTO() {
        return licenseDTO;
    }

    public void setLicenseDTO(LicenseDTO licenseDTO) {
        this.licenseDTO = licenseDTO;
    }
}
