package jcms.repository;

import jcms.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findTopByPublicationdateAsc(Pageable pageable);
}
