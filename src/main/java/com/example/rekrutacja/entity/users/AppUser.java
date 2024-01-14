package com.example.rekrutacja.entity.users;

import com.example.rekrutacja.entity.AppUserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AppUser implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String pesel;

    private String surname;

    private String login;

    private String password;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private AppUserRole role;

    @Builder.Default
    @Column(columnDefinition = "VARCHAR(30) DEFAULT " + "'" + ActivityStatus.Names.ACTIVE_NAME + "'")
    @Enumerated(value = EnumType.STRING)
    private ActivityStatus activityStatus = ActivityStatus.ACTIVE;

    private Boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.roleName()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
