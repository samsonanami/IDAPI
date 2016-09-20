package com.fintech.orion.dataabstraction.services;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.reflections.Reflections;

import java.util.Set;

public class ServicesTestsRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ClientLicenseServiceTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        Reflections reflections = new Reflections("com.fintech.orion.dataabstraction.services");

        Set<Class<? extends Object>> allClasses =
                reflections.getSubTypesOf(Object.class);
    }

}
