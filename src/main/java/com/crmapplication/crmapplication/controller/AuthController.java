package com.crmapplication.crmapplication.controller;

import com.crmapplication.crmapplication.entity.Role;
import com.crmapplication.crmapplication.entity.User;
import com.crmapplication.crmapplication.payload.JWTAuthResponse;
import com.crmapplication.crmapplication.payload.LoginDto;
import com.crmapplication.crmapplication.payload.SignUpDto;
import com.crmapplication.crmapplication.repositories.RoleRepository;
import com.crmapplication.crmapplication.repositories.UserRepository;
import com.crmapplication.crmapplication.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import
        org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.regex.Pattern;
import java.util.Collections;
import java.util.regex.Matcher;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {


//    @Autowired
//    private EmailServiceImpl emailservice;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@Valid @RequestBody LoginDto
                                                                    loginDto){
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

// get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);
        log.info("User successfully Logged in");

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto){

// add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            log.info("User trying to signup with username which is already taken");
            return new ResponseEntity<>("Username is already taken!",
                    HttpStatus.BAD_REQUEST);

        }

// add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            log.info("User trying to signup with email which is already taken");
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

//        // validate email format
//        if(!isValidEmail(signUpDto.getEmail())) {
//            throw new InvalidEmailFormatException("Invalid email format");
//        }

// create user object

        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);
        log.info("User successfully registered");

//        // send email
//        String subject = "Welcome to Nector!";
//        String content = "Hello " + user.getName() + ",\n\n"
//                + "Thank you for registering with us. We look forward to serving you!\n\n"
//                + "Best regards,\n"
//                + "Team Nector";
//        emailservice.sendSimpleMessage(user.getEmail(), subject,content);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }


//    private boolean isValidEmail(String email) {
//        if(email == null) {
//            return false;
//        }
//
//        // regular expression to validate email format
//        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,}$";
//        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(email);
//
//        return matcher.find();
//    }
}