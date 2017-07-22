package jcms.controller;

import jcms.model.Role;
import jcms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/api")
public class RoleController {
	private static final String PRIVATE_PATH = "/private";
	private static final String ROOT_PATH = "/roles";

	@Autowired
	RoleService roleService;

	@RequestMapping(value = PRIVATE_PATH + ROOT_PATH, method = RequestMethod.GET)
	ResponseEntity<?> getRoles() {
		List<Role> listOfRoles = roleService.findAll();
		if (listOfRoles != null) {
			return ResponseEntity.status(HttpStatus.OK).body(listOfRoles);
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not get roles.");
	}
}
