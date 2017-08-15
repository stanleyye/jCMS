package jcms.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/private/config-details")
public class ConfigDetailsController {
	private static final String BUNDLE_DETAILS_PATH = "/bundle";
	private static final String DATABASE_DETAILS_PATH = "/database";
	private static final String MEMORY_USAGE_DETAILS_PATH = "/memory";

	@RequestMapping(value = BUNDLE_DETAILS_PATH, method = RequestMethod.GET)
	public ResponseEntity<?> getWebpackBundleFilesDetail() {
		final Map<String, Long> fileSizesHashMap;
		try {
			fileSizesHashMap =
				getFileSizesInNonNestedDirectory(Paths.get("src/main/resources/static/build"));
		} catch (IOException ioException) {
			ioException.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body("Error reading bundle files");
		}
		return ResponseEntity.status(HttpStatus.OK).body(fileSizesHashMap);
	}

	@RequestMapping(value = DATABASE_DETAILS_PATH, method = RequestMethod.GET)
	public ResponseEntity<?> getDatabaseDetails() {
		return null;
	}

	@RequestMapping(value = MEMORY_USAGE_DETAILS_PATH, method = RequestMethod.GET)
	public ResponseEntity<?> getServerMemoryUsageDetails() {
		final Map<String, Long> memoryDetailsHashMap = getMemoryUsage();
		final List<Map<String, Long>> responseBodyList = new ArrayList<>();
		responseBodyList.add(memoryDetailsHashMap);
		return ResponseEntity.status(HttpStatus.OK).body(responseBodyList);
	}

	/**
	 * Get sizes of regular files in a specified directory.
	 *
	 * @param path The specified path to search
	 * @return a hashmap containing the file name and the file size (in bytes)
	 */
	@Cacheable
	private Map<String, Long> getFileSizesInNonNestedDirectory(Path path) throws IOException{
		final Map<String, Long> fileSizeHashMap = new HashMap<>();

		Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				// The size of non-regular files are implementation specific and therefore unspecified
				if (attrs.isRegularFile()) {
					String fileName = file.getFileName().toString();

					// The file names should be unique but if it isn't, do nothing.
					// If the file name is unique, get the size and add it to the hash map
					if (!fileSizeHashMap.containsKey(fileName)) {
						fileSizeHashMap.put(fileName, attrs.size());
					}
				}
				return FileVisitResult.CONTINUE;
			}
		});

		return fileSizeHashMap;
	}

	/**
	 * Get memory usage of the current Java server/program.
	 *
	 * @return a hashmap containing the memory usage details
	 */
	@Cacheable
	private Map<String, Long> getMemoryUsage() {
		final Map<String, Long> memoryDetailsHashMap = new HashMap<>();

		long totalMemory = Runtime.getRuntime().totalMemory();
		long freeMemory = Runtime.getRuntime().freeMemory();
		long usedMemory = totalMemory - freeMemory;

		memoryDetailsHashMap.put("Total", totalMemory);
		memoryDetailsHashMap.put("Free", freeMemory);
		memoryDetailsHashMap.put("Used", usedMemory);

		return memoryDetailsHashMap;
	}
}
