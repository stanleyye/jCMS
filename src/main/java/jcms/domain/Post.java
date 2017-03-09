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
    private String title;
    private String summary;
    private String content;
    @ManyToOne
    private User author;

    public Post() {}

    public Post(String title, String summary, String content, User author) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.author = author;

        System.out.println("object created");
        //System.out.println("object publication date: " + this.publicationDate);
        System.out.println("object title: " + this.title);
        System.out.println("object content: " + this.content);
        System.out.println("object author: " + this.author);
    }

}
