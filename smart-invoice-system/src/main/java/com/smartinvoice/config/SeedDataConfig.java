package com.smartinvoice.config;

import com.smartinvoice.entity.Vendor;
import com.smartinvoice.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SeedDataConfig {

    @Bean
    CommandLineRunner seed(VendorRepository vendors) {
        return args -> {
            if (vendors.count() == 0) {
                vendors.save(Vendor.builder().name("Acme Supplies Pvt Ltd").gstin("27ABCDE1234F1Z5").status(Vendor.Status.VERIFIED).build());
                vendors.save(Vendor.builder().name("NextGen Traders").gstin("27PQRSX5678Y1Z9").status(Vendor.Status.PENDING).build());
            }
        };
    }
}
