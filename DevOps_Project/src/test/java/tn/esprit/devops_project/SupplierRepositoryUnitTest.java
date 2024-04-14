package tn.esprit.devops_project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SupplierRepositoryUnitTest {

    @Mock
    SupplierRepository supplierRepository;

    @InjectMocks
    SupplierServiceImpl supplierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void destroy() {
        supplierRepository.deleteAll();
    }




    @Test
    void save() {
        Supplier supplier = new Supplier();
        supplier.setCode("123");
        supplier.setLabel("Supplier ABC");

        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        Supplier result = supplierService.addSupplier(supplier);

        assertEquals("123", result.getCode());
        assertEquals("Supplier ABC", result.getLabel());
    }

    @Test
    void deleteById() {
        Long supplierId = 1L;

        supplierService.deleteSupplier(supplierId);

        // Verify that the deleteById method is invoked once with the correct argument
        verify(supplierRepository, times(1)).deleteById(supplierId);
    }
}
