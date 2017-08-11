package jcms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/private")
public class ConfigDetailsController {
	// TODO: use Redis to cache
	public ResponseEntity<?> getWebpackBundleFilesDetail() {
		return null;
	}

	public ResponseEntity<?> getDatabaseDetails() {
		return null;
	}

	public ResponseEntity<?> getServerMemoryUsageDetails() {
		return null;
	}
}
