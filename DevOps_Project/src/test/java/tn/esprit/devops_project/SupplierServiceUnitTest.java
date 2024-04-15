package tn.esprit.devops_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SupplierServiceUnitTest {

    @Mock
    SupplierRepository supplierRepository;

    @InjectMocks
    SupplierServiceImpl supplierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retrieveAllSuppliers() {
        // Mock data
        Supplier supplier1 = new Supplier(1L, "code1", "label1");
        Supplier supplier2 = new Supplier(2L, "code2", "label2");

        List<Supplier> suppliers = Arrays.asList(supplier1, supplier2);

        // Mocking behavior
        when(supplierRepository.findAll()).thenReturn(suppliers);

        // Perform the test
        List<Supplier> result = supplierService.retrieveAllSuppliers();

        // Verify the interactions
        verify(supplierRepository, times(1)).findAll();

        // Assertions
        assertEquals(2, result.size());
    }


}
