package com.rakhmatullo.market.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String fullName;
    private int parentId;
    private int status;

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority;
        switch (status) {
            case 1:
                authority = new SimpleGrantedAuthority(Role.MANAGER.name());
                break;
            case 2:
                authority = new SimpleGrantedAuthority(Role.SELLER.name());
                break;
            case 3:
                authority = new SimpleGrantedAuthority(Role.SALES_ASSISTANT.name());
                break;
            case 4:
                authority = new SimpleGrantedAuthority(Role.CLIENT.name());
                break;
            default:
                authority = new SimpleGrantedAuthority(null);
                break;

        }
        return Collections.singleton(authority);
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
        return true;
    }
}
