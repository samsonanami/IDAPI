package com.fintech.orion.hermesagentservices.transmission.request.body.jenid.image;

import com.fintech.orion.common.exceptions.request.body.ImageBodyCreatorException;
import com.fintech.orion.common.exceptions.resource.Base64ServiceException;
import com.fintech.orion.hermesagentservices.resources.Base64ServiceInterface;
import com.fintech.orion.hermesagentservices.resources.ResourceProviderServiceInterface;
import com.fintech.orion.hermesagentservices.transmission.payload.model.jenid.Image;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by TharinduMP on 10/21/2016.
 * provide an image with image data string in JPEG format
 */
public class JPEGImageBodyCreator extends AbstractImageBodyCreator implements ImageBodyCreatorInterface {

    @Autowired
    private ResourceProviderServiceInterface resourceProviderService;

    @Autowired
    private Base64ServiceInterface base64Service;

    @Override
    public Image createImage(String resourcePath) throws ImageBodyCreatorException {
        try (FileInputStream stream = resourceProviderService.getResource(resourcePath)) {
            Image image = getDefaultImage();
            image.setImageData("data:image/jpeg;base64," + base64Service.fileToBase64(stream));
            return image;
        } catch (Base64ServiceException | IOException e) {
            throw new ImageBodyCreatorException(e);
        }
    }
}
