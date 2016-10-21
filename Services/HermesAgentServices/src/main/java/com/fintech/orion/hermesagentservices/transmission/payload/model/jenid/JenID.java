package com.fintech.orion.hermesagentservices.transmission.payload.model.jenid;

/**
 * Created by TharinduMP on 10/19/2016.
 * JenID Model for JSON
 */
public class JenID {

    private InputData inputData;
    private OutputData outputData;

    public InputData getInputData() {
        return inputData;
    }

    public void setInputData(InputData inputData) {
        this.inputData = inputData;
    }

    public OutputData getOutputData() {
        return outputData;
    }

    public void setOutputData(OutputData outputData) {
        this.outputData = outputData;
    }
}
