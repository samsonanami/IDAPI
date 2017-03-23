package com.fintech.orion.documentverification.common.mrz;

import com.fintech.orion.documentverification.common.exception.DrivingLicenseMRZDecodingException;
import com.fintech.orion.documentverification.common.exception.MRZDecodingException;
import com.fintech.orion.documentverification.common.mrz.factory.MrzFactory;
import com.fintech.orion.documentverification.common.mrz.filter.factory.FilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TharinduMP on 3/15/2017.
 */
@Component
public class IrlDrivingLicenseMRZDecodingStrategy implements MRZDecodingStrategy {

    @Autowired
    private MrzFactory filteredMrzFactory;

    @Autowired
    private FilterFactory filterFactory;

    @Override
    public MRZDecodeResults decode(String mrz) throws MRZDecodingException {
        MRZDecodeResults mrzDecodeResults = new MRZDecodeResults();
        try {

            Mrz irelandMrz = filteredMrzFactory.getMrz(mrz, "irelandDl", filterFactory.getFilter("FillerFilter"));

            // Surname
            mrzDecodeResults.setSurname(irelandMrz.getFeature("Surname"));

            // Driving License Number
            mrzDecodeResults.setDrivingLicenseNumber(irelandMrz.getFeature("DrivingLicNum"));


        } catch (Exception e) {
            throw new DrivingLicenseMRZDecodingException("Not well formatted drivingLicense MRZ or not well set configuration properties Exception " + mrz, e);
        }
        return mrzDecodeResults;
    }

}
