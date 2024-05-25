package org.micro.documentmanager.Models;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.micro.documentmanager.Enumeration.Authority;
import org.micro.documentmanager.Enumeration.Converter.RoleConverter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@JsonInclude(NON_DEFAULT)
public class RoleEntity extends Auditable{
    private String name; // user or admin;

    @Convert(converter = RoleConverter.class)
    private Authority authority; // read, write, update, delete
}
