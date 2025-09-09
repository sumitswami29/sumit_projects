package com.smartinvoice.repository;

import com.smartinvoice.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByGstin(String gstin);
    Optional<Vendor> findByNameIgnoreCase(String name);
}
