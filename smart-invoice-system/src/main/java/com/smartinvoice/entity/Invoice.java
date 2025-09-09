package com.smartinvoice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String invoiceNumber;

    @ManyToOne
    private Vendor vendor;

    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private String currency;

    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private Status status = Status.DRAFT;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceLineItem> lineItems = new ArrayList<>();

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments = new ArrayList<>();

    public enum Status { DRAFT, UNDER_REVIEW, APPROVED, REJECTED }
}
