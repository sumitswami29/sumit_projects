package com.smartinvoice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Vendor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(unique = true)
    private String gstin;

    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    public enum Status { VERIFIED, PENDING, BLACKLISTED }
}
