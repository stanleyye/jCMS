package jcms.service;

import jcms.model.Post;

import java.util.List;

public interface PostService {
	List<Post> findAll();
	Post getOne(Integer id);
	void save(Post post);
}
