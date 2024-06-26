package org.micro.documentmanager.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.micro.documentmanager.Exception.ApiException;
import org.micro.documentmanager.Domain.RequestContext;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public class Auditable {

    @Id
    @SequenceGenerator(
            name = "primary_key_seq",
            sequenceName = "primary_key_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_key_seq"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    private String refId = new AlternativeJdkIdGenerator().generateId().toString();

    @NotNull
    private Long createdBy;

    @NotNull
    private Long updatedBy;

    @NotNull
    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @CreatedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    private void prePersist() {
        var userId = RequestContext.getUserId();
        if (userId == null) {
            throw new ApiException("Cannot insert record without user id");
        }
        setCreatedBy(userId);
        setCreatedAt(now());
        setUpdatedBy(userId);
        setUpdatedAt(now());
    }

    @PreUpdate
    private void preUpdate() {
        var userId = RequestContext.getUserId();
        if (userId == null) {
            throw new ApiException("Cannot update record without user id");
        }
        setUpdatedBy(userId);
        setUpdatedAt(now());
    }
}
