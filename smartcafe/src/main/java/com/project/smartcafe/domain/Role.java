package com.project.smartcafe.domain;

import lombok.Data;
import javax.persistence.*;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@Table(name = "security_role")
@NoArgsConstructor
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    public Role(long i, String role) {
        this.id = i;
        this.roleName = role;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }

}
