package com.smartinvoice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Approval {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Invoice invoice;

    private String approverEmail;
    private OffsetDateTime decidedAt;
    private String decision; // APPROVE or REJECT
    private String comments;
}
