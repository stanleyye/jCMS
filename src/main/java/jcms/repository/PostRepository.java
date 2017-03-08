package jcms.repository;

import jcms.domain.Post;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Created by stanley on 04/03/17.
 */


public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    //public Post findById(Long id);
}
