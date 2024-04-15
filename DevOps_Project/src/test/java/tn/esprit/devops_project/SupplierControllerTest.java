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
import java.util.Arrays;
import java.util.List;
import tn.esprit.devops_project.controllers.SupplierController;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.services.Iservices.ISupplierService;

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

    // Other test methods for different scenarios
}
