package jcms.controller;

import jcms.model.Post;
import jcms.model.User;
import jcms.repository.postService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/posts")
public class PostController {
	public static final String ROOT_PATH = "/";

	@Autowired
	private PostService postService;

	/**
	 * Create a new post
	 */
	@RequestMapping(value = ROOT_PATH, method = RequestMethod.POST)
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
	 * Get all posts
	 */
	@RequestMapping(value = ROOT_PATH, method = RequestMethod.GET)
	public ResponseEntity<?> getAllPosts() {
		List<Post> listOfAllPosts = postService.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(listOfAllPosts);
	}

	/**
	 * Get a specific post
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPost(@RequestParam Integer id) {
		// TODO: Test out whether the @JsonIgnore properties are working as intended.
		Post post = postService.findById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(post);
	}
}
