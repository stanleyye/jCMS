package jcms.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by stanley on 04/03/17.
 */

@Data
@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "content")
    private String content;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn
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
