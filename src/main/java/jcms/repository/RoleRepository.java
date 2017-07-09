package jcms.repository;

import jcms.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigInteger;

@RepositoryRestResource(exported = false)
public interface RoleRepository extends JpaRepository<Role, BigInteger> {
    Role findById(BigInteger id);
}
