package jcms.service;

import jcms.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
	void delete(Integer id);
	List<Post> findAll();
	Page<Post> findAll(Pageable pageable);
	Post findOne(Integer id);
	void save(Post post);
}
