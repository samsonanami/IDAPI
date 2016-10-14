package com.fintech.orion.dataabstraction.helper;

import org.junit.Ignore;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
@Ignore
public class GenerateTimestampTest {

    @Test
    public void shouldReturnTimestampWhenGetTimestampCalled() {
        Timestamp timestamp = GenerateTimestamp.timestamp();
        Date date= new Date();
        Timestamp now = new Timestamp(date.getTime());
        assertEquals(timestamp, now);
    }

}
