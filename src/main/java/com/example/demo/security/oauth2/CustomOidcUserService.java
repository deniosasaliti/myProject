package com.example.demo.security.oauth2;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.repos.RoleRepository;
import com.example.demo.security.PrincipalDetails;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
public class CustomOidcUserService extends OidcUserService {
    private final UserService userService;
    private final RoleRepository roleRepository;


    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        Role userRole = roleRepository.findByName("user");
        User user = userService.findUserById(6L);
        user.setRole(userRole);
        return PrincipalDetails.create(user, oidcUser.getAttributes());
    }
}
