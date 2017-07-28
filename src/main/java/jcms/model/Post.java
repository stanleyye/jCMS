package jcms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="post")
public class Post {
	@JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @JsonProperty
	@Column(name ="publication_date", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date publicationDate;

    @JsonProperty
    @Column(name = "title")
    private String title;

    @JsonProperty
    @Column(name = "summary")
    private String summary;

    @JsonProperty
    @Column(name = "content")
    private String content;

    @JsonProperty
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn
    private User author;
}
