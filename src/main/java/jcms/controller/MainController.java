package jcms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The main controller to handle routes that display a view.
 */

@Controller
public class MainController {
	// TODO: Condense down the URL mappings
    @RequestMapping(
    	value = {
			"/",
			"/admin",
			"/admin/comments",
			"/admin/dashboard",
			"/admin/posts",
			"/admin/pages",
			"/admin/settings",
			"/admin/users",
			"/login"
		},
		method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
