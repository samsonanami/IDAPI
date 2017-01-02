package com.fintech.orion.documentverification.custom.common;

/**
 * Created by sasitha on 12/29/16.
 */
public class TestDataProvider {

    public static String ocrResponseString  ="{\n" +
            "  \"verificationRequestId\": \"58d74245-75f0-470a-be32-3287d5950993\",\n" +
            "  \"status\": \"processing_successful\",\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"id\": \"surname\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"passport##surname\",\n" +
            "          \"value\": \"SORRELL\",\n" +
            "          \"confidence\": 84\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"drivingLicenseFront##surname\",\n" +
            "          \"value\": \"SORFIELL\",\n" +
            "          \"confidence\": 79\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"given_name\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"passport##given_name\",\n" +
            "          \"value\": \"PHILIP MARK\",\n" +
            "          \"confidence\": 91\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"drivingLicenseFront##given_name\",\n" +
            "          \"value\": \"PHILIP MARK\",\n" +
            "          \"confidence\": 87\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"date_of_birth\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"passport##date_of_birth\",\n" +
            "          \"value\": \"20 JAN_ /JAN 59\",\n" +
            "          \"confidence\": 92\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"drivingLicenseFront##date_of_birth\",\n" +
            "          \"value\": \"20.01.1959\",\n" +
            "          \"confidence\": 89\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"place_of_birth\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"passport##place_of_birth\",\n" +
            "          \"value\": \"SOUTHBND —ON-SEA\",\n" +
            "          \"confidence\": 84\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"drivingLicenseFront##place_of_birth\",\n" +
            "          \"value\": \"UNITED KINGDOM\",\n" +
            "          \"confidence\": 87\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"date_of_issue\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"drivingLicenseFront##date_of_issue\",\n" +
            "          \"value\": \"05.05.2016\",\n" +
            "          \"confidence\": 89\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"passport##date_of_issue\",\n" +
            "          \"value\": \"09 FEB /FEV 12\",\n" +
            "          \"confidence\": 90\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"issuing_authorithy\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"drivingLicenseFront##issuing_authorithy\",\n" +
            "          \"value\": \"DVLA\",\n" +
            "          \"confidence\": 86\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"passport##issuing_authorithy\",\n" +
            "          \"value\": \"IPS\",\n" +
            "          \"confidence\": 94\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"date_of_expiry\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"passport##date_of_expiry\",\n" +
            "          \"value\": \"09 NOV /NOV 22\",\n" +
            "          \"confidence\": 93\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"drivingLicenseFront##date_of_expiry\",\n" +
            "          \"value\": \"04.05.2026\",\n" +
            "          \"confidence\": 87\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"MRZ_Line\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"drivingLicenseFront##MRZ_Line\",\n" +
            "          \"value\": \"SOHFIESOIZOQPMSJP &\",\n" +
            "          \"confidence\": 67\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"family_name\",\n" +
            "      \"value\": [\n" +
            "        \n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"parents_given_name\",\n" +
            "      \"value\": [\n" +
            "        \n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"sex\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"passport##sex\",\n" +
            "          \"value\": \"M\",\n" +
            "          \"confidence\": 90\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"passport_number\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"passport##passport_number\",\n" +
            "          \"value\": \"761335999\",\n" +
            "          \"confidence\": 86\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"nationality\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"passport##nationality\",\n" +
            "          \"value\": \"BRITISH CITIZEN\",\n" +
            "          \"confidence\": 92\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"place_of_issue\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"passport##place_of_issue\",\n" +
            "          \"value\": \"GBR\",\n" +
            "          \"confidence\": 91\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"MRZ_line1\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"passport##MRZ_line1\",\n" +
            "          \"value\": \"P<GBRSORRELL<<PHI LI P<MARK<<<<<<<<<<<<<<<<<<<\",\n" +
            "          \"confidence\": 90\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"MRZ_line2\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"passport##MRZ_line2\",\n" +
            "          \"value\": \"7613359992GBR5901205M2211097<<<<<<<<<<<<<<04\",\n" +
            "          \"confidence\": 81\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"address\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"drivingLicenseFront##address\",\n" +
            "          \"value\": \"31 WILTON GROVE. LONDT. TICK!\",\n" +
            "          \"confidence\": 81\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"processing_failure\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "          \"id\": \"drivingLicenseFront##processing_failure\",\n" +
            "          \"value\": \"\",\n" +
            "          \"confidence\": 0\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"passport##processing_failure\",\n" +
            "          \"value\": \"\",\n" +
            "          \"confidence\": 0\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";

}
