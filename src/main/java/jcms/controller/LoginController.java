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
public class LoginController {
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

    /**
     * Returns the base url of the request url string
     *
     * @param request The HTTP request
     * @return the base URL string
     */
    private String getBaseUrl(HttpServletRequest request) {
        String baseUrl = request
                .getRequestURL()
                .substring(0, request.getRequestURL().length() - request.getRequestURI().length())
                + request.getContextPath();
        return baseUrl;
    }

    /**
     * Set the HTTP header location field to the base url
     *
     * @param httpHeader The HTTP header to edit
     * @param request The HTTP Request to extract base url from
     * @throws URISyntaxException
     */
    private void setLocationToBaseUrlInHttpHeader(HttpHeaders httpHeader, HttpServletRequest request) throws URISyntaxException {
        URI location = new URI(getBaseUrl(request));
        httpHeader.setLocation(location);
    }
}
