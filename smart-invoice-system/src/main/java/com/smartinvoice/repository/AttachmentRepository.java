package com.smartinvoice.repository;

import com.smartinvoice.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> { }
