package jcms.repository;

import jcms.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
	//List<Post> findTopByPublicationdateAsc(Pageable pageable);
}
