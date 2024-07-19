package com.miliancode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {
    private static final String PREFIX = "ROLE_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, name = "name")
    private String roleNameString;

    @Override
    public String getAuthority() {
        return PREFIX + roleNameString;
    }

    public RoleName getRoleName() {
        return RoleName.valueOf(roleNameString);
    }

    public void setRoleName(RoleName roleName) {
        this.roleNameString = roleName.name();
    }

    public enum RoleName {
        USER,
        ADMIN
    }
}
