package jcms.controller;

import jcms.model.Post;
import jcms.model.User;
import jcms.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/posts")
public class PostController {
	public static final String CREATE_PATH = "/create";
	public static final String GET_PATH = "/";

    @Autowired
    private PostRepository postRepository;

    /**
     * GET endpoint for the get posts path
     */
    @RequestMapping(value = GET_PATH, method = RequestMethod.GET)
	public ResponseEntity<?> getPosts() {
    	List<Post> listOfAllPosts = postRepository.findAll();
    	return ResponseEntity.status(HttpStatus.FOUND).body(listOfAllPosts);
	}

    /**
     * POST endpoint for the create posts path
     */
    @RequestMapping(value = CREATE_PATH, method = RequestMethod.POST)
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        try {
			postRepository.save(post);
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
        	System.out.println(ex.getStackTrace());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving the post");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}
