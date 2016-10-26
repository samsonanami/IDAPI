package com.fintech.orion.hermesagentservices.transmission.request.body;

import com.fintech.orion.common.exceptions.request.BodyServiceException;
import com.fintech.orion.common.exceptions.request.body.ImageBodyCreatorException;
import com.fintech.orion.dto.resource.ResourceDTO;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorFactoryInterface;
import com.fintech.orion.hermesagentservices.transmission.payload.model.jenid.Image;
import com.fintech.orion.hermesagentservices.transmission.payload.model.jenid.InputData;
import com.fintech.orion.hermesagentservices.transmission.payload.model.jenid.InputImage;
import com.fintech.orion.hermesagentservices.transmission.payload.model.jenid.JenID;
import com.fintech.orion.hermesagentservices.transmission.request.body.jenid.image.ImageBodyCreatorInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by TharinduMP on 10/19/2016.
 * JenIdBody Creator
 */
public class JenIdBody implements BodyServiceInterface {

    @Autowired
    private ImageBodyCreatorInterface imageBodyCreator;

    @Autowired
    private ValidatorFactoryInterface validatorFactory;

    @Override
    public JenID createJSONBody(Map<String, String> configurations, List<ResourceDTO> resourceList, Map extras) throws BodyServiceException {
        try {

            //validate configurations
            validatorFactory.getValidator("jenIdConfigurationValidator").validate(configurations);

            if (resourceList != null && resourceList.size() == 1) {
                //create image object
                Image image = imageBodyCreator.createImage(resourceList.get(0).getLocation());

                //create input image list
                List<InputImage> inputImages = new ArrayList<>();

                //create input image
                InputImage inputImage = new InputImage();
                inputImage.setImage(image);

                inputImages.add(inputImage);

                //create input data object
                InputData inputData = new InputData();
                inputData.setInputImages(inputImages);

                //add client id
                inputData.setClientID(configurations.get("body.clientID"));

                //include it in jen id object
                JenID jenID = new JenID();
                jenID.setInputData(inputData);
                return jenID;
            } else {
                throw new BodyServiceException("resourceList is null or does not contain the necessary resources");
            }
        } catch (ValidatorException | IOException | ImageBodyCreatorException e) {
            throw new BodyServiceException(e);
        }
    }
}
