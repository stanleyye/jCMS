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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {
    // We need a signing key, so we'll create one just for this example. Usually
    // the key would be read from your application configuration instead.
    // TODO: move this key elsewhere
    private Key key = MacProvider.generateKey();

    private String JwtCookieName = "jCMSCookie";
    @Autowired
    private UserService userService;

    private String getBaseUrl(HttpServletRequest request) {
        String baseUrl = request
                .getRequestURL()
                .substring(0, request.getRequestURL().length() - request.getRequestURI().length())
                + request.getContextPath();
        return baseUrl;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> login(
            @RequestParam(value="email") String email,
            @RequestParam(value="password") String password,
            HttpServletRequest request,
            HttpServletResponse response) {
        // TODO: Sanitize the parameters for possible security issues
        // Check if there are any matching Jwt cookies
        String jwtSubject = "";
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(JwtCookieName)) {
                jwtSubject = Jwts.parser()
                        .setSigningKey(key)
                        .parseClaimsJws(cookie.getValue())
                        .getBody()
                        .getSubject();
                if (jwtSubject.equals(email)) {
                    // TODO: Set location field in response header and then return the Response entity
                }
            }
        }

        ResponseEntity incorrectUserOrPassResponseEntity =
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Either the username or password is incorrect.");

        // Find the user
        User user = userService.findByEmail(email);

        // Return bad HTTP response if user does not exist
        if (user == null) {
            return incorrectUserOrPassResponseEntity;
        }

        // Return bad HTTP response if password does not match
        boolean isMatchingPassword = UserServiceImplementation.PASSWORD_ENCODER.matches(password, user.getPassword());
        if (!isMatchingPassword) {
            return incorrectUserOrPassResponseEntity;
        }

        // Sign the JWT using SHA-512 and compact it to a String form
        String compactJws = Jwts.builder()
                .setSubject(user.getEmail())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        // Redirect the user to the main page once they log in by setting the response header location
        HttpHeaders responseHeaders = new HttpHeaders();

        try {
            URI location = new URI(getBaseUrl(request));
            responseHeaders.setLocation(location);
        } catch (URISyntaxException e) {
            // TODO: create a logger to handle these errors / exceptions
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Store JWT inside a cookie.
        // Note: Storing JWT on the client side in localStorage/sessionStorage makes it vulnerable to XSS.
        // Normally, a cookie's state needs to be stored on the server but with JWT, it encapsulates
        // everything needed for the request. Cookies are vulnerable to CSRF but that threat can be
        // greatly reduced with token patterns or a framework's CSRF protection mechanisms
        // TODO: Set secure / domain / http only parameters for the cookie
        final Cookie jwtCookie = new Cookie(JwtCookieName, compactJws);
        // Max age of cookie = 7 days
        jwtCookie.setMaxAge(604800);
        response.addCookie(jwtCookie);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.FOUND);
    }

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ResponseEntity registerNewUser(@Valid User user) {
//        User isExistingUser = userService.findByEmail(user.getEmail());
//
//        // Return bad request HTTP status if user already exists
//        if (isExistingUser != null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with the same email already exists.");
//        }
//
//        // Save the user if user does not exist
//        userService.saveUser(user);
//
//        return ResponseEntity.ok("User has been created.");
//    }
}
