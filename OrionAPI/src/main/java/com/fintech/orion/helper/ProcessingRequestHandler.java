package com.fintech.orion.helper;

import com.fintech.orion.coreservices.*;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.*;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import com.fintech.orion.dataabstraction.models.verificationresult.VerificationRequest;
import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.processconfig.ProcessConfigDTO;
import com.fintech.orion.dto.processingrequest.ProcessingRequestDTO;
import com.fintech.orion.dto.processingstatus.ProcessingStatusDTO;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProcessingRequestHandler implements ProcessingRequestHandlerInterface {

    @Autowired
    private ClientServiceInterface clientServiceInterface;

    @Autowired
    private ProcessingRequestServiceInterface processingRequestServiceInterface;

    @Autowired
    private ProcessServiceInterface processServiceInterface;

    @Autowired
    private ProcessTypeServiceInterface processTypeServiceInterface;

    @Autowired
    private ResourceServiceInterface resourceServiceInterface;

    @Autowired
    private ProcessingStatusServiceInterface processingStatusServiceInterface;

    @Autowired
    private String inspectionImageUrl;

    @Autowired
    private ProcessConfigServiceInterface processConfigServiceInterface;

    @Override
    public String saveVerificationProcessData(String accessToken, List<VerificationProcess> verificationProcessList) throws ItemNotFoundException {
        ClientDTO clientDTO = clientServiceInterface.findByAuthToken(accessToken);

        ProcessingRequestDTO processingRequestDTO = processingRequestServiceInterface.save(clientDTO);
        ProcessingStatusDTO processingStatusDTO = processingStatusServiceInterface.findByStatus(Status.PROCESSING_REQUESTED);

        for (VerificationProcess v : verificationProcessList) {
            ProcessTypeDTO processTypeDTO = processTypeServiceInterface.findByType(v.getVerificationProcessType());
            ProcessDTO processDTO = processServiceInterface.save(processTypeDTO, processingRequestDTO, processingStatusDTO);
            for (com.fintech.orion.dataabstraction.models.verificationprocess.Resource r : v.getResources()) {
                resourceServiceInterface.saveOrUpdate(r.getResourceId(), processDTO.getId());
            }
        }
        return processingRequestDTO.getProcessingRequestIdentificationCode();
    }

    @Override
    public VerificationRequest getVerificationRequestData(String accessToken, String verificationRequestId) throws ItemNotFoundException {
        ProcessingRequestDTO processingRequestDTO = processingRequestServiceInterface.findByIdIdentificationCode(verificationRequestId);

        VerificationRequest verificationRequest = new VerificationRequest();
        List<com.fintech.orion.dataabstraction.models.verificationresult.VerificationProcess> verificationProcessList = new ArrayList<>();
        verificationRequest.setVerificationRequestId(processingRequestDTO.getProcessingRequestIdentificationCode());
        for (ProcessDTO p : processingRequestDTO.getProcesses()) {
            com.fintech.orion.dataabstraction.models.verificationresult.VerificationProcess verificationProcess =
                    new com.fintech.orion.dataabstraction.models.verificationresult.VerificationProcess();
            verificationProcess.setVerificationProcessId(p.getProcessIdentificationCode());
            verificationProcess.setStatus(p.getProcessingStatusDTO().getStatus());
            if(p.getProcessingStatusDTO().getStatus().equals(Status.PROCESSING_COMPLETE)){
                verificationProcess.setData(p.getResponseDTO().getExtractedJson());
            }
            verificationProcessList.add(verificationProcess);
        }
        verificationRequest.setVerificationProcesses(verificationProcessList);
        return verificationRequest;
    }

    @Override
    public BufferedImage getResourceData(String accessToken, String verificationProcessId, String id) throws IOException, ParseException, ItemNotFoundException {
        String url = inspectionImageUrl + id;

        ProcessDTO processDTO = processServiceInterface.findByIdentificationCode(verificationProcessId);

        ProcessTypeDTO processTypeDTO = processTypeServiceInterface.findById(processDTO.getProcessTypeDTO().getId());

        String responseString = sendGet(url, processTypeDTO.getId());
        JSONObject json = (JSONObject)new JSONParser().parse(responseString);
        return getBufferedImageFromString(json.get("base64Content").toString());
    }

    private String sendGet(String url, int processType) throws IOException, ParseException, ItemNotFoundException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        List<ProcessConfigDTO> processConfigDTOs = processConfigServiceInterface.findById(processType);

        con.setRequestMethod("GET");
//        byte[] bytesEncoded = Base64.encodeBase64(("Fintech" + ":" + "xGH22979Hos2wx4K").getBytes());
        byte[] bytesEncoded = DatatypeConverter.parseBase64Binary(String.valueOf(("Fintech" + ":" + "xGH22979Hos2wx4K").getBytes()));
        String test = "Basic " + new String(bytesEncoded);
        con.setRequestProperty("Authorization", test);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private BufferedImage getBufferedImageFromString(String base64String) throws IOException {
        BufferedImage image = null;
        byte[] imageByte;

//        BASE64Decoder decoder = new BASE64Decoder();
//        imageByte = decoder.decodeBuffer(base64String);

        String str = DatatypeConverter.printBase64Binary(base64String.getBytes());
        imageByte = DatatypeConverter.parseBase64Binary(str);

        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();
        return image;
    }
}
