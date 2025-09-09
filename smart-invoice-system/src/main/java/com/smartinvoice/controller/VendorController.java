package com.smartinvoice.controller;

import com.smartinvoice.entity.Vendor;
import com.smartinvoice.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final VendorRepository vendorRepository;

    @GetMapping
    public List<Vendor> all() {
        return vendorRepository.findAll();
    }

    @PostMapping
    public Vendor create(@RequestBody Vendor v) {
        return vendorRepository.save(v);
    }
}
