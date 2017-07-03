package jcms.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "roleName")
    private String roleName;
}
