package com.fintech.orion.documentverification.common.address;

import com.fintech.orion.documentverification.common.exception.AddressValidatingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;

/**
 * This class compares the decoded address elements.
 * Created by MudithaJ on 12/22/2016.
 */
@Component
public class AddressCompare implements Address {

    @Autowired
    private AddressDecoding addressDecoder;

    @Override
    public AddressCompareResult compare(String addressOne, String addressTwo) throws AddressValidatingException {
        try {
            AddressCompareResult result = new AddressCompareResult();
            AddressDecodeResults addressOneresult;
            AddressDecodeResults addressTwoResult;
            HashMap<String, Boolean> resultMap = new HashMap<String, Boolean>();
            String message = "";
            addressOneresult = addressDecoder.decode(addressOne);
            addressTwoResult = addressDecoder.decode(addressTwo);

            result.setAddressOne(addressOne);
            result.setAddressTwo(addressTwo);
            result.setAddressOneType(addressOneresult.getAddressType());
            result.setaddressTwoType(addressOneresult.getAddressType());

            resultMap.put("AddressType", this.addressTypeCompare(addressOneresult, addressTwoResult));
            resultMap.put("PropertyNumber", this.addressNumberCompre(addressOneresult, addressTwoResult));
            resultMap.put("FlatNumber", this.addressFlatNumberCompre(addressOneresult, addressTwoResult));
            resultMap.put("PostalCode", this.addressPostalCodeCompre(addressOneresult, addressTwoResult));

            Iterator i = resultMap.entrySet().iterator();

            while (i.hasNext()) {
                HashMap.Entry set = (HashMap.Entry) i.next();
                message = message + set.getKey() + ":" + set.getValue() + ",";
            }
            if (resultMap.containsValue(false)) {
                result.setResult(false);
            } else {
                result.setResult(true);
            }
            result.setMessage(message);

            return result;
        } catch (NullPointerException e) {
            throw new AddressValidatingException("Not well formatted address  or not well set configuration properties Exception", e);
        }
    }

    public Boolean addressNumberCompre(AddressDecodeResults addressOne, AddressDecodeResults addressTwo) {
        Boolean compare = false;
        if (addressOne.getNumber().equals(addressTwo.getNumber())) {
            compare = true;
        }

        return compare;
    }

    public Boolean addressFlatNumberCompre(AddressDecodeResults addressOne, AddressDecodeResults addressTwo) {
        Boolean compare = false;
        if (addressOne.getFlatNumber().equals(addressTwo.getFlatNumber())) {
            compare = true;
        }

        return compare;
    }

    public Boolean addressPostalCodeCompre(AddressDecodeResults addressOne, AddressDecodeResults addressTwo) {
        Boolean compare = false;
        if (addressOne.getPostalCode().equals(addressTwo.getPostalCode())) {
            compare = true;
        }

        return compare;
    }

    public Boolean addressTypeCompare(AddressDecodeResults addressOne, AddressDecodeResults addressTwo) {
        Boolean compare = false;
        if (addressOne.getAddressType().equals(addressTwo.getAddressType())) {
            compare = true;
        }

        return compare;
    }
}
