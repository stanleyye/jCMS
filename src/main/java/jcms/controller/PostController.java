package jcms.controller;

import jcms.model.Post;
import jcms.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
	@RequestMapping(
		value = PRIVATE_PATH + ROOT_PATH, method = RequestMethod.POST
	)
	public ResponseEntity<?> createPost(@RequestBody Post post) {
		try {
			postService.save(post);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getStackTrace());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								 .body("Error saving the post");
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

		// Check if the page and size params are present and return the pages
		// accordingly.
		if (isSizeParamPresent) {
			Page<Post> posts = null;
			// Check if the page param is present. If it is, return the page.
			// Otherwise, return the first page
			if (isPageParamPresent) {
				posts = postService.findAll(
					new PageRequest(
						page.get(),
						size.get(),
						new Sort(
							new Sort.Order(Sort.Direction.DESC, "publicationDate")
						)
					)
				);
			} else {
				posts = postService.findAll(
					new PageRequest(
						1,
						size.get(),
						new Sort(
							new Sort.Order(Sort.Direction.DESC, "publicationDate")
						)
					)
				);
			}
			return ResponseEntity.status(HttpStatus.OK).body(posts);
		} else if (isPageParamPresent) {
			// Size is not specified so return an error.
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								 .body("Missing size parameter");
		}

		// If no page or size param is specified, return all the posts
		List<Post> listOfPosts = postService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(listOfPosts);
	}

	/**
	 * Get a specific post
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPost(@PathVariable("id") Integer id) {
		// TODO: Test out whether the @JsonIgnore properties are working as intended.
		Post post = postService.findOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}
}
