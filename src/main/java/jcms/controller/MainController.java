package jcms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The main controller to handle routes that display a view.
 */

@Controller
public class MainController {
	// TODO: adminRegex needs to be updated when sub-sub routes are added
	private String adminRegex = "/admin(\/(comments|dashboard|posts|pages|settings|users)){0,1}"

    @RequestMapping(value = {"/", adminRegex, "/login"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
