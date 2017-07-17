package jcms.model;

import javax.persistence.*;
import lombok.Data;

/**
 * Role DAO. Used to specify and restrict the access level of a user.
 */

@Data
@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name")
    private String roleName;
}
