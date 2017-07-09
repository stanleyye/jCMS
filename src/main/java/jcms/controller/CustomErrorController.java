package jcms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcms.error.DebugError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

/**
 * Controller for unhandled errors (such as unhandled route errors).
 * Based off of Joni Karppinen's Github gist (https://gist.github.com/jonikarppinen/662c38fb57a23de61c8b)
 */
@Controller
public class CustomErrorController implements ErrorController{
    private final static String ERROR_PATH = "/error";

    @Value("${debugMode}")
	private boolean isDebugMode;

    @Autowired
	private ErrorAttributes errorAttributes;

    @RequestMapping(value = ERROR_PATH)
    public String error(Model model, HttpServletRequest request, HttpServletResponse response) {
    	int httpErrorStatus = response.getStatus();

        model.addAttribute("errorStatus", httpErrorStatus);
        model.addAttribute("errorMessage", "Oops, looks like something went wrong.");

        model.addAttribute("isDebugMode", isDebugMode);

        if (isDebugMode) {
        	model.addAttribute(
        		"debugError",
				new DebugError(httpErrorStatus, getErrorAttributes(request, isDebugMode))
			);
		}
        return "error";
    }

	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
	}

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
