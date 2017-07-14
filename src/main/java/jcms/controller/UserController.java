package jcms.controller;

import java.net.URI;
import java.net.URISyntaxException;

import jcms.model.User;
import jcms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {
    private final static String REGISTER_PATH = "/register";

    @Autowired
    private UserService userService;

    /**
     * POST Method for the /register user endpoint.
     */
    @RequestMapping(value = REGISTER_PATH, method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody User newUser) {
        boolean isExistingUsername = userService.existsByUsername(newUser.getUsername());
        boolean isExistingEmail = userService.existsByEmail(newUser.getEmail());

        if (isExistingUsername) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The username has already been taken.");
        }

        if (isExistingEmail) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The specified email already exists.");
        }

        // Save the user
        userService.saveUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
