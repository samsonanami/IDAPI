package com.fintech.orion.api.service.request;

import com.fintech.orion.coreservices.*;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;
import com.fintech.orion.dataabstraction.models.verificationprocess.Resource;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.processingrequest.ProcessingRequestDTO;
import com.fintech.orion.dto.processingstatus.ProcessingStatusDTO;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by sasitha on 11/29/16.
 */
public class ProcessingRequestHandlerTest {

    @InjectMocks
    private ProcessingRequestHandler processingRequestHandler;

    @Mock
    private ClientServiceInterface clientServiceInterface;

    @Mock
    private ProcessingRequestServiceInterface processingRequestServiceInterface;

    @Mock
    private ProcessingStatusServiceInterface processingStatusServiceInterface;

    @Mock
    private ProcessTypeServiceInterface processTypeServiceInterface;

    @Mock
    private ResourceServiceInterface resourceServiceInterface;

    @Mock
    private ProcessServiceInterface processServiceInterface;

    private String client = "zoneAdmin";
    private ClientDTO clientDTO = new ClientDTO();
    private ProcessingRequestDTO processingRequestDTO = new ProcessingRequestDTO();
    private ProcessingStatusDTO processingStatusDTO = new ProcessingStatusDTO();
    private ProcessTypeDTO addressVerificationDTO = new ProcessTypeDTO();
    private ProcessTypeDTO idVerificationDTO = new ProcessTypeDTO();
    private ProcessDTO idVerificationProcessDTO = new ProcessDTO();
    private ProcessDTO addressVerificationProcessDTO = new ProcessDTO();

    private List<VerificationProcess> verificationProcessList = new ArrayList<>();
    private VerificationProcess idVerification = new VerificationProcess();
    private VerificationProcess addressVerification = new VerificationProcess();
    private List<Resource> idResourceList = new ArrayList<>();
    private List<Resource> addressResourceList = new ArrayList<>();
    private Resource idResource = new Resource();
    private Resource addressResource = new Resource();


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        //autowired data setup
        processingRequestDTO.setProcessingRequestIdentificationCode("qwert");
        idVerificationDTO.setType("idVerification");
        addressVerificationDTO.setType("addressVerification");

        idVerificationProcessDTO.setId(1);
        addressVerificationProcessDTO.setId(2);

        //processing request setup
        clientDTO.setUserName(client);
        idVerification.setVerificationProcessType("idVerification");
        idResource.setResourceName("id");
        idResource.setResourceId("123");
        idResourceList.add(idResource);
        idVerification.setResources(idResourceList);

        addressVerification.setVerificationProcessType("addressVerification");
        addressResource.setResourceName("utilityBill");
        addressResource.setResourceId("1234");
        addressResourceList.add(addressResource);
        addressVerification.setResources(addressResourceList);

        verificationProcessList.add(idVerification);
        verificationProcessList.add(addressVerification);

    }

    @Test
    public void should_save_and_get_processing_request_id() throws ItemNotFoundException {
        Mockito.when(clientServiceInterface.findByUserName(client)).thenReturn(clientDTO);
        Mockito.when(processingRequestServiceInterface.save(clientDTO)).thenReturn(processingRequestDTO);
        Mockito.when(processingStatusServiceInterface.findByStatus(Status.PROCESSING_REQUESTED)).thenReturn(processingStatusDTO);
        Mockito.when(processTypeServiceInterface.findByType("addressVerification")).thenReturn(addressVerificationDTO);
        Mockito.when(processTypeServiceInterface.findByType("idVerification")).thenReturn(addressVerificationDTO);
        Mockito.when(processServiceInterface.save(idVerificationDTO, processingRequestDTO, processingStatusDTO)).thenReturn(idVerificationProcessDTO);
        Mockito.when(processServiceInterface.save(addressVerificationDTO, processingRequestDTO, processingStatusDTO)).thenReturn(addressVerificationProcessDTO);
        Mockito.doNothing().when(resourceServiceInterface).update(idResource.getResourceId(), idVerificationProcessDTO.getId(), idResource.getResourceName());
        Mockito.doNothing().when(resourceServiceInterface).update(addressResource.getResourceId(), addressVerificationDTO.getId(), addressResource.getResourceName());
        Assert.assertEquals(processingRequestHandler.saveVerificationProcessData("zoneAdmin", verificationProcessList), "qwert");
    }
}
