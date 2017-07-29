package jcms.controller;

import java.util.List;

import jcms.model.Role;
import jcms.model.User;
import jcms.model.UserRole;
import jcms.security.JWTPayload;
import jcms.security.RegisterUserCredentials;
import jcms.security.TokenService;
import jcms.service.RoleService;
import jcms.service.UserRoleService;
import jcms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class UserController {
	private static final String PRIVATE_PATH = "/private";
	private static final String ROOT_PATH = "/users";

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    /**
     * Create a new user
     */
    @RequestMapping(value = PRIVATE_PATH + ROOT_PATH, method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody RegisterUserCredentials newUserCredentials,
										HttpServletRequest request) {
		JWTPayload jwtPayload = TokenService.getJwtCookiePayload(request.getCookies());
		final String roleOfCurrentRequestUser = jwtPayload.getRole();

		if (roleOfCurrentRequestUser == "author") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Access has been denied.");
		}

        boolean isExistingUsername = userService.existsByUsername(newUserCredentials.getUsername());
        boolean isExistingEmail = userService.existsByEmail(newUserCredentials.getEmail());

        if (isExistingUsername) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The username has already been taken.");
        }

        if (isExistingEmail) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The specified email already exists.");
        }

        User newUser = new User(
        	newUserCredentials.getName(),
        	newUserCredentials.getUsername(),
			newUserCredentials.getEmail(),
			newUserCredentials.getPassword()
		);

        // Save the user
        User savedUser = userService.save(newUser);
        // If the user was successfully saved, then create a new user_role record to keep track of the user's
		// role level
        if (savedUser != null) {
        	Role role = roleService.getOne(newUserCredentials.getRoleId());
			if (role != null){
				userRoleService.save(new UserRole(
					savedUser,
					role
				));

				return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
			}

			// If role can't be saved, then there is no use for a user with no role.
			// Hence, we remove it.
			userService.removeById(savedUser.getId());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User could not be saved.");
    }

    @RequestMapping(value = PRIVATE_PATH + ROOT_PATH, method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(Integer id) {
    	// TODO: deleting a user requires removing it from the 'user' table
		// and the 'user_role' table

		return null;
	}

    /**
     * Gets the list of users.
     */
    @RequestMapping(value = PRIVATE_PATH + ROOT_PATH, method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers() {
    	List<User> listOfUsers = userService.findAll();
    	if (listOfUsers != null) {
			return ResponseEntity.status(HttpStatus.OK).body(listOfUsers);
		}
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not get list of users.");
    }
}
