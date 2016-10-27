package com.fintech.orion.hermesagentservices.transmission.payload.model.jenid;

/**
 * Created by TharinduMP on 10/19/2016.
 * Image Model
 */
public class Image {
    private Integer pixelWidth;
    private Integer pixelHeight;
    private Number mmWidth;
    private Number mmHeight;
    private Integer size;
    private Integer cropped;
    private String imageData;

    public Integer getPixelWidth() {
        return pixelWidth;
    }

    public void setPixelWidth(Integer pixelWidth) {
        this.pixelWidth = pixelWidth;
    }

    public Integer getPixelHeight() {
        return pixelHeight;
    }

    public void setPixelHeight(Integer pixelHeight) {
        this.pixelHeight = pixelHeight;
    }

    public Number getMmWidth() {
        return mmWidth;
    }

    public void setMmWidth(Number mmWidth) {
        this.mmWidth = mmWidth;
    }

    public Number getMmHeight() {
        return mmHeight;
    }

    public void setMmHeight(Number mmHeight) {
        this.mmHeight = mmHeight;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCropped() {
        return cropped;
    }

    public void setCropped(Integer cropped) {
        this.cropped = cropped;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
}
