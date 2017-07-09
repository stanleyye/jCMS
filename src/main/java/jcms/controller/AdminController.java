package jcms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	public final static String ADMIN_PATH = "/admin";

	/*
	 * GET method for the /admin endpoint
	 */
	@RequestMapping(value = ADMIN_PATH, method = RequestMethod.GET)
	public String getAdminPage() {
		return "admin";
	}
}
