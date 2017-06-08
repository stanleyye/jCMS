package jcms.controller;

import jcms.model.User;
import jcms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@Valid User user) {
        User isExistingUser = userService.findByEmail(user.getEmail());

        if (isExistingUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist.");
        } else {
            return ResponseEntity.ok("");
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity registerNewUser(@Valid User user) {
        User isExistingUser = userService.findByEmail(user.getEmail());

        if (isExistingUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with the same email already exists.");
        }

        // Save the user if user does not exist
        userService.saveUser(user);

        return ResponseEntity.ok("User has been created.");
    }
}
