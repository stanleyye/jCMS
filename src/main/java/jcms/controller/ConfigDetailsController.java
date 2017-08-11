package jcms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/private")
public class ConfigDetailsController {

	// TODO: use Redis to cache
	public ResponseEntity<?> getWebpackBundleFilesDetail() {
		try {
			final Map<String, Long> fileSizesHashMap = getFileSizesInNonNestedDirectory("/src/main/resources/bundles/");
			return null;
		} catch (IOException ioException) {
			return null;
		}

		return null;
	}

	public ResponseEntity<?> getDatabaseDetails() {
		return null;
	}

	public ResponseEntity<?> getServerMemoryUsageDetails() {
		final Map<String, Long> memoryDetailsHashMap = getMemoryUsage();
		return null;
	}

	/**
	 * Get sizes of regular files in a specified directory.
	 *
	 * @param path The specified path to search
	 * @return a hashmap containing the file name and the file size (in bytes)
	 */
	private Map<String, Long> getFileSizesInNonNestedDirectory(String path) {
		final Map<String, Long> fileSizeHashMap = new HashMap<>();

		Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throw IOException {
				// The size of non-regular files are implementation specific and therefore unspecified
				if (attrs.isRegularFile()) {
					String fileName = file.getFileName();

					// The file names should be unique but if it isn't, do nothing.
					// If the file name is unique, get the size and add it to the hash map
					if (!fileSizeHashMap.containsKey(fileName)) {
						fileSizeHashMap.put(fileName, attrs.size());
					}
				}
			}
		});
	}

	/**
	 * Get memory usage of the current Java server/program.
	 *
	 * @return a hashmap containing the memory usage details
	 */
	public Map<String, Long> getMemoryUsage() {
		final Map<String, Long> memoryDetailsHashMap = new HashMap<>();

		long totalMemory = Runtime.getRuntime().totalMemory();
		long freeMemory = Runtime.getRuntime().freeMemory();
		long usedMemory = totalMemory - freeMemory;

		memoryDetailsHashMap.put("total", totalMemory);
		memoryDetailsHashMap.put("free", freeMemory);
		memoryDetailsHashMap.put("used", usedMemory);

		return memoryDetailsHashMap;
	}
}
