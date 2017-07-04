package jcms.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for unhandled errors (such as unhandled route errors).
 */
@Controller
public class CustomErrorController implements ErrorController{
    private final static String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public String error(Model model, HttpServletResponse response) {
        model.addAttribute("errorStatus", response.getStatus());
        model.addAttribute("errorMessage", "Oops, looks like something went wrong.");
        return "error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
