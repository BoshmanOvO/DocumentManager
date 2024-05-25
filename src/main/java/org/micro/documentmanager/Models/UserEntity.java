package org.micro.documentmanager.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static jakarta.persistence.FetchType.*;

@Getter @Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonInclude(NON_DEFAULT)
public class UserEntity extends Auditable{

    @Column(unique = true, updatable = false, nullable = false)
    private String userId; // can find acc to this

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email; // can find acc to this

    private String phone;
    private String imageUrl;
    private String bio;

    // will use this for security
    private Integer loginAttempts;
    private LocalDateTime lastLogin;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean enabled;
    private boolean isMfa;

    @JsonIgnore
    private String qrCodeSecret;

    @Column(columnDefinition = "text") // store the image in text format
    private String qrCodeImageUri;

    @ManyToOne(fetch = EAGER) // many users can have one role,and one role can have many users --
    // get the role when a user is fetched
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"), // from this class (UserEntity)
                    inverseJoinColumns = @JoinColumn(
                            name = "role_id",referencedColumnName = "id"
            )
    )
    private RoleEntity roles;

}
