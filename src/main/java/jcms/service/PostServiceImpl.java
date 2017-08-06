package jcms.service;

import jcms.model.Post;
import jcms.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostRepository postRepository;

	@CacheEvict(value = "post", key = "#id")
	public void delete(Integer id) {
		postRepository.delete(id);
	}

	public List<Post> findAll() {
		return postRepository.findAll();
	}

	public Page<Post> findAll(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@Cacheable(value = "post", key = "#id", unless = "#result == null")
	public Post findOne(Integer id) {
		return postRepository.findOne(id);
	}

	public void save(Post post) {
		postRepository.save(post);
	}
}
