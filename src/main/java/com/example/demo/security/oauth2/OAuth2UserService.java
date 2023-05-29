package com.example.demo.security.oauth2;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.repos.RoleRepository;
import com.example.demo.security.PrincipalDetails;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@AllArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;
    private final RoleRepository roleRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        System.out.println("OAuth2UserService_loadUser");


            return processOAuth2User(userRequest, oauth2User);


    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oauth2UserRequest, OAuth2User oauth2User) {
//        OAuth2UserInfo oauth2UserInfo = OAuth2UserInfoFactory
//                .getOAuth2UserInfo(oauth2UserRequest.getClientRegistration().getRegistrationId(),
//                        oauth2User.getAttributes());
//        if (StringUtils.isEmpty(oauth2UserInfo.getEmail())) {
//            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
//        }
//
//        Optional<UserEntity> userOptional = userRepository.findByEmail(oauth2UserInfo.getEmail());
//
//        UserEntity user;
//        if (userOptional.isPresent()) {
//            user = userOptional.get();
//            user = updateExistingUser(user, oauth2UserInfo);
//        } else {
//            user = registerNewUser(oauth2UserRequest, oauth2UserInfo);
//        }
        System.out.println("OAuth2UserService_processOAuth2User");
        Role userRole = roleRepository.findByName("user");
        User user = userService.findUserById(6L);
        user.setRole(userRole);
        return PrincipalDetails.create(user, oauth2User.getAttributes());
    }

}
