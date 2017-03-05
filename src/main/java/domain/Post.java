package domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by stanley on 04/03/17.
 */

@Data
@Entity
public class Post {

    private @Id @GeneratedValue Long id;
    private @Temporal(TemporalType.TIMESTAMP) java.util.Date publicationDate;
    private String title;
    private String summary;
    private String content;
    private int author;

    private Post() {}

    public Post(String title, String summary, String content, int author) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.author = author;
    }

}
