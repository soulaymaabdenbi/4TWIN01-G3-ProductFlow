package tn.esprit.devops_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.repositories.SupplierRepository;

@SpringBootApplication
public class DevOps_ProjectSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevOps_ProjectSpringBootApplication.class, args);
    }

    private final SupplierRepository supplierRepository;

    @Autowired
    public DevOps_ProjectSpringBootApplication(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Bean
    ApplicationRunner init() {
        return args -> {
            // Save sample suppliers
            supplierRepository.save(new Supplier(1L, "code1", "label1", SupplierCategory.ORDINAIRE, null));
            supplierRepository.save(new Supplier(2L, "code2", "label2", SupplierCategory.CONVENTIONNE, null));
        };
    }
}
