package jcms.repository;

import jcms.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PostRepository extends JpaRepository<Post, BigInteger> {
    Post findById(BigInteger id);
}
