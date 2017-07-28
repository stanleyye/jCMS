package jcms.repository;

import jcms.model.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {
	List<Post> findAll();
}
