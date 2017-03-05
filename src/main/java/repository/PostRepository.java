package repository;

import domain.Post;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by stanley on 04/03/17.
 */
public interface PostRepository extends CrudRepository<Post, Long> {

    public Post findById(Long id);
}
