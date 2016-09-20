package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.LicenseRepository;
import com.fintech.orion.dataabstraction.repositories.LicenseRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class LicenseServiceTest extends ObjectCreator {

    private License license;
    private List<License> licenses;

    @Before
    public void setup() {
        licenses = new ArrayList<>();
        licenses.add(createLicenseObject());
        licenses.add(createLicenseObject());
        licenses.add(createLicenseObject());
        license = new License();
    }

    @Test
    public void should_returnListOfLicenses_when_getLicenseListCalled() throws Exception {
        LicenseServiceInterface serviceInterface = new LicenseService();
        LicenseRepositoryInterface repositoryInterfaceMock = mock(LicenseRepository.class);
        when(repositoryInterfaceMock.getAll()).thenReturn(licenses);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        List<License> found = serviceInterface.getLicenseList();
        assertEquals(3, found.size());
        for(License l : found){
            assertTrue(licenses.contains(l));
        }
    }

    @Test
    public void should_returnLicenseObject_when_getLicenseByIdCalled() throws ItemNotFoundException {
        LicenseServiceInterface serviceInterface = new LicenseService();
        LicenseRepositoryInterface repositoryInterfaceMock = mock(LicenseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(license);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        License found = serviceInterface.getLicenseById(1);
        assertTrue(license.equals(found));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_returnItemNotFoundException_when_getLicenseByIdCalled_for_incorrectId() throws ItemNotFoundException {
        LicenseServiceInterface serviceInterface = new LicenseService();
        LicenseRepositoryInterface repositoryInterfaceMock = mock(LicenseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(license);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.getLicenseById(2);
    }

    @Test
    public void should_saveLicenseObject_when_saveLicenseCalled() {
        LicenseServiceInterface serviceInterface = new LicenseService();
        LicenseRepositoryInterface repositoryInterfaceMock = mock(LicenseRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveLicense(license);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(license);
    }

    @Test
    public void should_updateLicenseObject_when_updateLicenseCalled() {
        LicenseServiceInterface serviceInterface = new LicenseService();
        LicenseRepositoryInterface repositoryInterfaceMock = mock(LicenseRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.updateLicense(license);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(license);
    }

    @Test
    public void should_deleteLicenseObject_when_deleteLicenseByIdCalled() throws ItemNotFoundException {
        LicenseServiceInterface serviceInterface = new LicenseService();
        LicenseRepositoryInterface repositoryInterfaceMock = mock(LicenseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(license);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteLicenseById(1);
        assertTrue(found);
    }

    @Test
    public void should_notDeleteItem_when_deleteLicenseByIdCalled_for_incorrect_id() throws ItemNotFoundException {
        LicenseServiceInterface serviceInterface = new LicenseService();
        LicenseRepositoryInterface repositoryInterfaceMock = mock(LicenseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(license);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteLicenseById(2);
        assertFalse(found);
    }

    @Test
    public void should_deleteLicenseObject_when_deleteLicenseCalled() {
        LicenseServiceInterface serviceInterface = new LicenseService();
        LicenseRepositoryInterface repositoryInterfaceMock = mock(LicenseRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.deleteLicense(license);
        verify(repositoryInterfaceMock, times(1)).delete(license);
    }
    
}
