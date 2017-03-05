package controller;

import domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import repository.PostRepository;

/**
 * Created by stanley on 04/03/17.
 */

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    /**
     * GET /create  --> Create a new post and save it in the database.
     */
    @RequestMapping("/create")
    @ResponseBody
    public String create() {

        try {
            Post post = new Post("Test Post", "hey", "l", 2);
            postRepository.save(post);
        }
        catch (Exception ex) {
            return "Error creating the post: " + ex.toString();
        }
        return "Post created";
    }
}
