package sch.cqre.api.domain;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Reaction", schema = "main")
@Getter @Setter
public class ReactionEntity {

    @Id
    @Column(name = "user_id")
    private long userId;

    @JsonIgnore
   // @ManyToOne(fetch = FetchType.LAZY)
    @Column(name="post_id")//, referencedColumnName = "post_id")
    private long postId;


    @Column(name = "reaction")
    private String reaction;

    @Builder
    public ReactionEntity(long userId, long postId, String reaction) {
        this.userId = userId;
        this.postId = postId;
        this.reaction = reaction;
    }

}
