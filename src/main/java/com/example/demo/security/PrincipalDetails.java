package com.example.demo.security;


import com.example.demo.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalDetails implements UserDetails,OAuth2User, OidcUser {


    private Long id;
    private String name;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private transient Map<String, Object> attributes;


    public static PrincipalDetails create(User user) {
        return PrincipalDetails.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())

                .authorities(user.getRole().getPermissions())
                .build();
    }


    public static PrincipalDetails create(User user, Map<String, Object> attributes) {
        PrincipalDetails principalDetails = PrincipalDetails.create(user);
        principalDetails.setAttributes(attributes);
        return principalDetails;
    }

//     private static List<GrantedAuthority> toGrantedAuthority (String role){
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(role));
//        return  authorities;
//    }


//    @Override
//    public Map<String, Object> getAttributes() {
//        return null;
//    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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


    @Override
    public Map<String, Object> getClaims() {
        return null;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return null;
    }

    @Override
    public OidcIdToken getIdToken() {
        return null;
    }
}

