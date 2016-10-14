package com.fintech.orion.helper;

import com.fintech.orion.coreservices.*;
import com.fintech.orion.dataabstraction.entities.orion.*;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.*;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import com.fintech.orion.dataabstraction.models.verificationresult.VerificationRequest;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
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
    private ProcessResourceServiceInterface processResourceServiceInterface;

    @Autowired
    private ResourceServiceInterface resourceServiceInterface;

    @Autowired
    private ProcessingStatusServiceInterface processingStatusServiceInterface;

    @Autowired
    private String inspectionImageUrl;

    @Override
    public String saveVerificationProcessData(String accessToken, List<VerificationProcess> verificationProcessList) throws ItemNotFoundException {
        Client client = clientServiceInterface.findByAuthToken(accessToken);

        ProcessingRequest processingRequest = processingRequestServiceInterface.save(client);
        ProcessingStatus processingStatus = processingStatusServiceInterface.findByStatus(Status.PROCESSING_REQUESTED);

        for (VerificationProcess v : verificationProcessList) {
            ProcessType processType = processTypeServiceInterface.findByType(v.getVerificationProcessType());
            Process process = processServiceInterface.save(processType, processingRequest, processingStatus);
            for (com.fintech.orion.dataabstraction.models.verificationprocess.Resource r : v.getResources()) {
                Resource resource = resourceServiceInterface.findByIdentificationCode(r.getResourceId());
                processResourceServiceInterface.save(process, resource, r.getResourceName());
            }
        }
        return processingRequest.getProcessingRequestIdentificationCode();
    }

    @Override
    public VerificationRequest getVerificationRequestData(String accessToken, String verificationRequestId) throws ItemNotFoundException {
        ProcessingRequest processingRequest = processingRequestServiceInterface.findByIdIdentificationCode(verificationRequestId);

        VerificationRequest verificationRequest = new VerificationRequest();
        List<com.fintech.orion.dataabstraction.models.verificationresult.VerificationProcess> verificationProcessList = new ArrayList<>();
        verificationRequest.setVerificationRequestId(processingRequest.getProcessingRequestIdentificationCode());
        for (Process p : processingRequest.getProcesses()) {
            com.fintech.orion.dataabstraction.models.verificationresult.VerificationProcess verificationProcess =
                    new com.fintech.orion.dataabstraction.models.verificationresult.VerificationProcess();
            verificationProcess.setVerificationProcessId(p.getProcessIdentificationCode());
            verificationProcess.setStatus(p.getProcessingStatus().getStatus());
            if(p.getProcessingStatus().getStatus().equals(Status.PROCESSING_COMPLETE)){
                verificationProcess.setData(p.getResponse().getExtractedJson());
            }
            verificationProcessList.add(verificationProcess);
        }
        verificationRequest.setVerificationProcesses(verificationProcessList);
        return verificationRequest;
    }

    @Override
    public BufferedImage getResourceData(String accessToken, String verificationProcessId, String id) throws IOException, ParseException {
        String url = inspectionImageUrl + id;
        String responseString = sendGet(url);
        JSONObject json = (JSONObject)new JSONParser().parse(responseString);
        return getBufferedImageFromString(json.get("base64Content").toString());
    }

    private String sendGet(String url) throws IOException, ParseException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        byte[] bytesEncoded = Base64.encodeBase64(("Fintech" + ":" + "xGH22979Hos2wx4K").getBytes());
        String test = "Basic " + new String(bytesEncoded);
        con.setRequestProperty("Authorization", test);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private BufferedImage getBufferedImageFromString(String base64String) throws IOException {
        BufferedImage image = null;
        byte[] imageByte;

        BASE64Decoder decoder = new BASE64Decoder();
        imageByte = decoder.decodeBuffer(base64String);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();
        return image;
    }
}
