package jcms.controller;

import jcms.model.Post;
import jcms.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class PostController {
	private static final String PRIVATE_PATH = "/private";
	private static final String PUBLIC_PATH = "/public";
	private static final String ROOT_PATH = "/posts";

	@Autowired
	private PostService postService;

	/**
	 * Create a new post
	 */
	@RequestMapping(value = PRIVATE_PATH + ROOT_PATH, method = RequestMethod.POST)
	public ResponseEntity<?> createPost(@RequestBody Post post) {
		try {
			postService.save(post);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getStackTrace());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving the post");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(post);
	}

	/**
	 * Get posts
	 */
	@RequestMapping(value = PUBLIC_PATH + ROOT_PATH, method = RequestMethod.GET)
	public ResponseEntity<?> getAllPosts(
		@RequestParam("page") Optional<Integer> page,
		@RequestParam("size") Optional<Integer> size) {
		boolean isPageParamPresent = page.isPresent();
		boolean isSizeParamPresent = size.isPresent();
		List<Post> listOfPosts = null;

		// If page and size parameters are present, do a db query with both.
		// If only the size param is present, do a db query with the offset param.
				// If only the page param is present, return a Bad Request Error.
		// Otherwise, do a query and return all the posts
		if (isPageParamPresent && isSizeParamPresent) {
			listOfPosts = postService.findTopByPublicationdateAsc(
				new PageRequest(page.get(), size.get());
			);
		} else if (isSizeParamPresent) {
			listOfPosts = postService.findTopByPublicationdateLimitBySize(
				size.get()
			);
		} else if (isPageParamPresent) {
			return ResponseEntity
								.status(HttpStatus.BAD_REQUEST)
								.body("Missing size parameter");
		} else {
			listOfPosts = postService.findAll();
		}

		return ResponseEntity.status(HttpStatus.OK).body(listOfPosts);
	}

	/**
	 * Get a specific post
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPost(@PathVariable("id") Integer id) {
		// TODO: Test out whether the @JsonIgnore properties are working as intended.
		Post post = postService.getOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}
}
