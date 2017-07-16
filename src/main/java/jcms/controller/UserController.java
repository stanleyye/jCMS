package jcms.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
@RequestMapping("/api/users")
public class UserController {
    private final static String ROOT_PATH = "/";

    @Autowired
    private UserService userService;

    /**
     * Create a new user
     */
    @RequestMapping(value = ROOT_PATH, method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
    	// TODO: Add checking for role level to see if the specified user is allowed to create a user or not
        boolean isExistingUsername = userService.existsByUsername(newUser.getUsername());
        boolean isExistingEmail = userService.existsByEmail(newUser.getEmail());

        if (isExistingUsername) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The username has already been taken.");
        }

        if (isExistingEmail) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The specified email already exists.");
        }

        // Save the user
        userService.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    /**
     * Gets the list of users.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers() {
    	System.out.println("testing");
    	List<User> listOfUsers = userService.findAll();
    	return ResponseEntity.status(HttpStatus.OK).body(listOfUsers);
    }
}
