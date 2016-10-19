package com.fintech.orion.hermesagentservices.transmission.payload.model.jenid;

/**
 * Created by TharinduMP on 10/19/2016.
 * InputImage Model
 */
public class InputImage {
    private MetaData metaData;
    private String inputImageType;
    private Image image;

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public String getInputImageType() {
        return inputImageType;
    }

    public void setInputImageType(String inputImageType) {
        this.inputImageType = inputImageType;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
