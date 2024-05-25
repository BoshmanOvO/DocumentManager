package org.micro.documentmanager.Models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.OnDelete;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static org.hibernate.annotations.OnDeleteAction.CASCADE;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credentials")
@JsonInclude(NON_DEFAULT)
public class CredentialEntity extends Auditable{
    private String password;

    @OneToOne // one user can have one credential
    @JoinColumn(name = "user_id", nullable = false) // flow -> it will make a column in this table with name user_id and it will reference to id column of UserEntity
    @OnDelete(action = CASCADE) // if user is deleted then delete the credential as well
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user_id")
    private UserEntity user;

    public CredentialEntity(UserEntity user, String password) {
        this.user = user;
        this.password = password;
    }
}
