package tn.esprit.devops_project;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.devops_project.controllers.SupplierController;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.services.Iservices.ISupplierService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(SupplierController.class)
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISupplierService supplierService;

    @Test
    public void testGetSuppliers() throws Exception {
        Supplier supplier1 = new Supplier(1L, "code1", "label1", SupplierCategory.ORDINAIRE, null);
        Supplier supplier2 = new Supplier(2L, "code2", "label2", SupplierCategory.CONVENTIONNE, null);
        List<Supplier> suppliers = Arrays.asList(supplier1, supplier2);

        when(supplierService.retrieveAllSuppliers()).thenReturn(suppliers);

        mockMvc.perform(get("/supplier"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].idSupplier").value(1))
                .andExpect(jsonPath("$[0].code").value("code1"))
                .andExpect(jsonPath("$[0].label").value("label1"))
                .andExpect(jsonPath("$[0].supplierCategory").value("ORDINAIRE"))
                .andExpect(jsonPath("$[1].idSupplier").value(2))
                .andExpect(jsonPath("$[1].code").value("code2"))
                .andExpect(jsonPath("$[1].label").value("label2"))
                .andExpect(jsonPath("$[1].supplierCategory").value("CONVENTIONNE"));
    }

    @Test
    public void testRetrieveSupplier() throws Exception {
        Supplier supplier = new Supplier(1L, "code1", "label1", SupplierCategory.ORDINAIRE, null);

        when(supplierService.retrieveSupplier(1L)).thenReturn(supplier);

        mockMvc.perform(get("/supplier/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idSupplier").value(1))
                .andExpect(jsonPath("$.code").value("code1"))
                .andExpect(jsonPath("$.label").value("label1"))
                .andExpect(jsonPath("$.supplierCategory").value("ORDINAIRE"));
    }

    @Test
    public void testAddSupplier() throws Exception {
        Supplier supplier = new Supplier(null, "code1", "label1", SupplierCategory.ORDINAIRE, null);
        Supplier savedSupplier = new Supplier(1L, "code1", "label1", SupplierCategory.ORDINAIRE, null);

        when(supplierService.addSupplier(any(Supplier.class))).thenReturn(savedSupplier);

        mockMvc.perform(post("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"code\": \"code1\", \"label\": \"label1\", \"supplierCategory\": \"ORDINAIRE\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idSupplier").value(1))
                .andExpect(jsonPath("$.code").value("code1"))
                .andExpect(jsonPath("$.label").value("label1"))
                .andExpect(jsonPath("$.supplierCategory").value("ORDINAIRE"));
    }

    @Test
    public void testModifySupplier() throws Exception {
        Supplier supplier = new Supplier(1L, "code1", "label1", SupplierCategory.ORDINAIRE, null);
        Supplier updatedSupplier = new Supplier(1L, "code1_updated", "label1_updated", SupplierCategory.CONVENTIONNE, null);

        when(supplierService.updateSupplier(any(Supplier.class))).thenReturn(updatedSupplier);

        mockMvc.perform(put("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"idSupplier\": 1, \"code\": \"code1_updated\", \"label\": \"label1_updated\", \"supplierCategory\": \"CONVENTIONNE\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idSupplier").value(1))
                .andExpect(jsonPath("$.code").value("code1_updated"))
                .andExpect(jsonPath("$.label").value("label1_updated"))
                .andExpect(jsonPath("$.supplierCategory").value("CONVENTIONNE"));
    }

    @Test
    public void testDeleteSupplier() throws Exception {
        mockMvc.perform(delete("/supplier/1"))
                .andExpect(status().isOk());
    }
}
