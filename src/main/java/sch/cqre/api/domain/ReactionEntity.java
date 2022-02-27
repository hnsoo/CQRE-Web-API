package sch.cqre.api.domain;

import java.util.Objects;

import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Reaction", schema = "main")
public class ReactionEntity {

    @Id
    @Column(name = "user_id")
    private int userId;


    @Column(name = "post_id")
    private int postId;


    @Column(name = "reaction")
    private String reaction;

    @Builder
    public ReactionEntity(int userId, int postId, String reaction) {
        this.userId = userId;
        this.postId = postId;
        this.reaction = reaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ReactionEntity that = (ReactionEntity) o;
        return postId == that.postId && userId == that.userId && reaction == that.reaction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId, reaction);
    }
}
