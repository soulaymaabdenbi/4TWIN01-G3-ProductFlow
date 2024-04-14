package tn.esprit.devops_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.ArrayList;
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
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier());
        suppliers.add(new Supplier());

        when(supplierRepository.findAll()).thenReturn(suppliers);

        List<Supplier> result = supplierService.retrieveAllSuppliers();

        assertEquals(2, result.size());
    }

    @Test
    void addSupplier() {
        Supplier supplier = new Supplier();
        supplier.setCode("123");
        supplier.setLabel("Supplier ABC");

        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        Supplier result = supplierService.addSupplier(supplier);

        assertEquals("123", result.getCode());
        assertEquals("Supplier ABC", result.getLabel());
    }

    @Test
    void updateSupplier() {
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(1L);
        supplier.setCode("123");
        supplier.setLabel("Supplier ABC");

        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        Supplier result = supplierService.updateSupplier(supplier);

        assertEquals(1L, result.getIdSupplier());
        assertEquals("123", result.getCode());
        assertEquals("Supplier ABC", result.getLabel());
    }

    @Test
    void deleteSupplier() {
        Long supplierId = 1L;

        supplierService.deleteSupplier(supplierId);

        // Verify that the deleteById method is invoked once with the correct argument
        verify(supplierRepository, times(1)).deleteById(supplierId);
    }

    @Test
    void retrieveSupplier() {
        Long supplierId = 1L;
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(supplierId);

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        Supplier result = supplierService.retrieveSupplier(supplierId);

        assertEquals(supplierId, result.getIdSupplier());
    }
}
