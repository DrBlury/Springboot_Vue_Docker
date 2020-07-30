package com.microservice.backend.navigation.controllers;

import com.lambdaworks.crypto.SCryptUtil;
import com.microservice.backend.entity.UserAccount;
import com.microservice.backend.entity.UserPrivileges;
import com.microservice.backend.enums.UserStatus;
import com.microservice.backend.generators.JWTBuilder;
import com.microservice.backend.repos.UserAccountRepository;
import com.microservice.backend.repos.UserPrivilegesRepository;
import com.microservice.backend.services.EmailService;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/register")
@NoArgsConstructor
@CrossOrigin
public class RegistrationController {
    @Autowired
    UserAccountRepository userAccountsRepository;

    @Autowired
    UserPrivilegesRepository userPrivilegesRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    JWTBuilder jwtBuilder;

    @RequestMapping("/create")
    public String create(@RequestParam String username, @RequestParam String password ) throws MessagingException {
        UserAccount userAccount = userAccountsRepository.findByUsername(username);

        if (userAccount == null) {
            userAccountsRepository.save(UserAccount.builder()
                    .username(username)
                    .password(SCryptUtil.scrypt(password, 2048, 8, 1))
                    .userStatus(UserStatus.ACTIVE)
                    .build()
            );

            userPrivilegesRepository.save(
                    UserPrivileges.builder()
                    .isAdmin(true)
                    .isModerator(true)
                    .username(username)
                    .build()
            );

            emailService.sendEmail("mail@blury.eu", "bensch.julian@gmail.com", "Hello!", "This is a test message!");
            return "created account!";
        }else {
            return "account already created.";
        }
    }

    @RequestMapping("/login")
    public String login(HttpServletResponse response, @RequestParam String username, @RequestParam String password) throws UnsupportedEncodingException {
        try {
            UserAccount userAccount = userAccountsRepository.findByUsername(username);
            Boolean check = SCryptUtil.check(password, userAccount.getPassword());
            if(check) {
                response.setHeader("Access-Control-Expose-Headers", "JWTToken");
                response.addHeader("jwttoken", jwtBuilder.generateJWTToken(username));
                return "successful login!";
            } else {
                return "wrong userdata!";
            }
        } catch (NullPointerException e) {
            return "No user with this account!";
        }
    }

    @RequestMapping("/try")
    public String tryOperation(@RequestHeader("jwttoken") String jwttoken) {
        boolean check = jwtBuilder.checkJWTToken(jwttoken);
        if(check) {
            return "Successful operation!";
        } else {
            return "Not authorized!";
        }
    }
}
