package com.rakhmatullo.market.auth;

import com.rakhmatullo.market.config.JwtService;
import com.rakhmatullo.market.exception.StatusNotValid;
import com.rakhmatullo.market.exception.UserExistedException;
import com.rakhmatullo.market.user.User;
import com.rakhmatullo.market.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService service;
    private final AuthenticationManager manager;


    public AuthenticationResponse register(
            RegisterRequest request) throws UserExistedException, StatusNotValid {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .parentId(request.getParentId())
                .status(request.getStatus())
                .build();
        Optional<User> userFromDb=userRepository.findByUsername(request.getUsername());
        if(request.getStatus()>4 ||request.getStatus()<1) {
            throw new StatusNotValid("status is not Valid");
        }
        if(userFromDb.isPresent()){
            throw new UserExistedException("This User already registered");
        } else {
            userRepository.save(user);
            var token = service.generateToken(user);
            return AuthenticationResponse.builder().token(token).build();
        }


    }

    public  AuthenticationResponse authenticate(AuthenticationRequest request) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        String token = service.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }
}
