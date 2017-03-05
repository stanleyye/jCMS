package jcms.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by stanley on 04/03/17.
 */

@Data
@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date publicationDate;

    //@NotNull
    private String title;

    //@NotNull
    private String summary;

    //@NotNull
    private String content;

    @NotNull
    private int author;

    public Post() {}

    public Post(String title, String summary, String content, int author) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.author = author;

        System.out.println("object created");
    }

}
