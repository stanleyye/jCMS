package jcms.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Key;
import jcms.model.User;
import jcms.service.UserService;
import jcms.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {
    private final static String LOGIN_PATH = "/login";
    private final static String REGISTER_PATH = "/register";

    @Autowired
    private UserService userService;

    /*
     * GET method for the /login endpoint.
     */
    @RequestMapping(value = LOGIN_PATH, method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    /*
     * POST method for the /login endpoint.
     */
//    @RequestMapping(value = LOGIN_PATH, method = RequestMethod.POST)
//    public @ResponseBody ResponseEntity<?> login(
////            @RequestParam(value="username") String username,
////            @RequestParam(value="password") String password,
//			@RequestBody User u,
//            HttpServletRequest request,
//            HttpServletResponse response) {
//        // TODO: Sanitize the parameters for possible security issues
//        HttpHeaders responseHeaders = new HttpHeaders();
//
//        System.out.println(u.getUsername());
//
//        String username ="dasfasdf";
//        String password = "asdfasdF";
//
//        /*
//         * Check if there are any matching Jwt cookies. If there are, redirect the request to the
//         * base URL.
//         */
//        if (isMatchingJwtSubject(request.getCookies(), jwtCookieName, username)) {
//            try {
//                setLocationToBaseUrlInHttpHeader(responseHeaders, request);
//                return new ResponseEntity<>(null, responseHeaders, HttpStatus.FOUND);
//            } catch (URISyntaxException e) {
//                // TODO: create a logger to handle these errors / exceptions
//                System.out.println(e.getMessage());
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//
//        ResponseEntity incorrectUserOrPassResponseEntity =
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Either the username or password is incorrect.");
//
//        // Find the user
//        User user = userService.findByUsername(username);
//
//        // Return bad HTTP response if user does not exist
//        if (user == null) {
//            return incorrectUserOrPassResponseEntity;
//        }
//
//        // Return bad HTTP response if password does not match
//        boolean isMatchingPassword = UserServiceImplementation.PASSWORD_ENCODER.matches(password, user.getPassword());
//        if (!isMatchingPassword) {
//            return incorrectUserOrPassResponseEntity;
//        }
//
//        try {
//            setLocationToBaseUrlInHttpHeader(responseHeaders, request);
//        } catch (URISyntaxException e) {
//            // TODO: create a logger to handle these errors / exceptions
//            System.out.println(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        response.addCookie(createJwtCookie(user.getUsername()));
//
//        return new ResponseEntity<>(null, responseHeaders, HttpStatus.FOUND);
//    }

    /*
     * GET method for the /login endpoint.
     */
    @RequestMapping(value = REGISTER_PATH, method = RequestMethod.GET)
    public String getRegisterPage() {
        return "register";
    }

    /*
     * POST Method for the /register endpoint.
     */
    @RequestMapping(value = REGISTER_PATH, method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> register(
            @RequestParam(value="name") String name,
            @RequestParam(value="username") String username,
            @RequestParam(value="email") String email,
            @RequestParam(value="password") String password) {
        // TODO: sanitize parameters
        boolean isExistingUsername = userService.existsByUsername(username);
        boolean isExistingEmail = userService.existsByEmail(email);

        if (isExistingUsername) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The username has already been taken.");
        }

        if (isExistingEmail) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The specified email already exists.");
        }

        // Create and save the user
        // TODO: Set up roles / admin levels
        User newUser = new User(name, username, email, password);
        userService.saveUser(newUser);

        return ResponseEntity.ok("");
    }

    /*
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

    /*
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
