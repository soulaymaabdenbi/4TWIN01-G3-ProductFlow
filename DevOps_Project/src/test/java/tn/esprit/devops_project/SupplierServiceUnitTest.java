package tn.esprit.devops_project;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SupplierServiceUnitTest {

    @Mock
    SupplierRepository supplierRepository;

    @InjectMocks
    SupplierServiceImpl supplierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retrieveSupplier() {
        // Given
        Long supplierId = 1L;
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(supplierId);
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        // When
        Supplier result = supplierService.retrieveSupplier(supplierId);

        // Then
        assertEquals(supplierId, result.getIdSupplier());
    }

    // Add more test methods for other service methods...
}
