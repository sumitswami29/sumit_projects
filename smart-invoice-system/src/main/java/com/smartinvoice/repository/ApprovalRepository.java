package com.smartinvoice.repository;

import com.smartinvoice.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<Approval, Long> { }
