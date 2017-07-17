package jcms.service;

import jcms.model.Post;
import jcms.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {
	@Autowired
	PostRepository postRepository;

	public List<Post> findAll() {
		return postRepository.findAll();
	}

	public List<Post> findTopByPublicationdateAsc(Pageable pageable) {
		return postRepository.findTopByPublicationdateAsc(pageable);

	public Post getOne(Integer id) {
		return postRepository.getOne(id);
	}

	public void save(Post post) {
		postRepository.save(post);
	}
}
